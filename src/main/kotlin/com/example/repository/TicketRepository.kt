package com.example.repository

import com.example.domain.Ticket
import io.micronaut.core.annotation.NonNull
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import java.util.*

@JdbcRepository(dialect = Dialect.POSTGRES)
interface TicketRepository : CrudRepository<Ticket, UUID> {
    fun findAllByLotteryId(lotteryId: UUID): MutableList<Ticket?>
    fun getTicketById(id: UUID): Ticket?
}