package com.example.service

import com.example.domain.Ticket
import com.example.repository.TicketRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.util.*

interface TicketService {
    fun reserveTickets(ticketNumbers: List<Int>): List<Ticket>
    fun getAllAvailableTickets(lotteryId: UUID): List<Ticket>
    fun getAllSoldTickets(lotteryId: UUID): List<Ticket>
}

@Singleton
class TicketServiceImpl(
    @Inject val ticketRepository: TicketRepository
) : TicketService {
    override fun reserveTickets(ticketNumbers: List<Int>): List<Ticket> {
        TODO("Not yet implemented")
    }

    override fun getAllAvailableTickets(lotteryId: UUID): List<Ticket> {
        val allTickets = ticketRepository.findAllByLotteryId(lotteryId)
        return allTickets.filterNotNull().filter {
            it.availableToPurchase
        }
    }

    override fun getAllSoldTickets(lotteryId: UUID): List<Ticket> {
        val allTickets = ticketRepository.findAllByLotteryId(lotteryId)
        return allTickets.filterNotNull().filter {
            !it.availableToPurchase
        }
    }
}