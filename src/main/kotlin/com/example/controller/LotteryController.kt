package com.example.controller

import com.example.domain.Lottery
import com.example.domain.LotteryWithPrizesInput
import com.example.domain.Prize
import com.example.domain.PrizeStoredInDb
import com.example.service.LotteryService
import com.example.service.PrizeService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import jakarta.annotation.security.PermitAll
import jakarta.inject.Inject
import java.util.*

@Controller("/lottery")
class LotteryController(
    @Inject val lotteryService: LotteryService,
    @Inject val prizeService: PrizeService
) {
    @Post("/createNew")
    @PermitAll // Not recommended, but quick fix in order to test
    fun createNewLottery(@Body lotteryWithPrizesInput: LotteryWithPrizesInput): HttpResponse<Lottery> {
        val lotteryInfo = Lottery(
            name = lotteryWithPrizesInput.name,
            tickets = lotteryWithPrizesInput.tickets
        )
        val lotteryId = lotteryInfo.id ?: UUID.randomUUID()
        val prizeInfo = lotteryWithPrizesInput.prizes.map {
            PrizeStoredInDb(
                name = it.name,
                cost = it.cost,
                stillAvailable = true,
                lotteryId = lotteryId
            )

        }
        val newLottery = lotteryService.createLottery(lotteryInfo)
        prizeService.storePrize(prizeInfo)
        return HttpResponse.created(newLottery)
    }

    @Get("/getCreated/{lotteryId}")
    @PermitAll
    fun getLottery(lotteryId: UUID): HttpResponse<Lottery?> {
        val lottery = lotteryService.getLotteryById(lotteryId)
        return if(lottery != null){
            HttpResponse.created(lottery)
        } else {
            HttpResponse.noContent()
        }

    }
}