package com.example.service

import com.example.domain.PrizeStoredInDb
import com.example.repository.PrizeRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.util.*
import kotlin.collections.List

interface PrizeService {
    fun storePrize(prizes: List<PrizeStoredInDb>)
    fun getNextPrice(lotteryId: UUID): PrizeStoredInDb
    fun updatePrize(prizeStoredInDb: PrizeStoredInDb)
}

@Singleton
class PrizeServiceImpl(
    @Inject val prizeRepository: PrizeRepository
) : PrizeService {
    override fun storePrize(prizes: List<PrizeStoredInDb>) {
        prizeRepository.saveAll(prizes)
    }

    override fun getNextPrice(lotteryId: UUID): PrizeStoredInDb {
        val prizes = prizeRepository.findAllByLotteryId(lotteryId)
        val nextPrize = prizes.filterNotNull()
            .filter { it.stillAvailable }
            .maxWith(Comparator.comparingDouble { it.cost })
        updatePrize(nextPrize.copy(stillAvailable = false))
        return nextPrize
    }

    override fun updatePrize(prizeStoredInDb: PrizeStoredInDb) {
        prizeRepository.update(prizeStoredInDb)
    }

}