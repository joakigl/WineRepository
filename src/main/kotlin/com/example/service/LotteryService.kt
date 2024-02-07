package com.example.service

import com.example.domain.Lottery
import com.example.domain.Ticket
import com.example.repository.LotteryRepository
import com.example.repository.TicketRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.util.UUID

interface LotteryService {
    fun createLottery(lottery: Lottery): Lottery
    fun getLotteryById(lotteryId: UUID): Lottery?
    fun selectWinnerTicket(lotteryId: UUID): Ticket
}

@Singleton
class LotteryServiceImpl(
    @Inject val lotteryRepository: LotteryRepository,
    @Inject val ticketRepository: TicketRepository,
    @Inject val ticketService: TicketService
) : LotteryService {
    override fun createLottery(lottery: Lottery): Lottery {
        val lotteryId = lottery.id ?: UUID.randomUUID()
        for (i in 1..lottery.tickets!!) { // Will not be null, as it is set if not provided
            val ticket = Ticket(
                number = i,
                availableToPurchase = true,
                lotteryId = lotteryId
            )
            ticketRepository.save(ticket)
        }
        return lotteryRepository.save(lottery)
    }

    override fun getLotteryById(lotteryId: UUID): Lottery? {
        return lotteryRepository.getById(lotteryId)
    }

    override fun selectWinnerTicket(lotteryId: UUID): Ticket {
        val allSoldTicketsToLottery = ticketService.getAllSoldTickets(lotteryId)
        return allSoldTicketsToLottery.random()
    }

}