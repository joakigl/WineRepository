package com.example.repository

import com.example.domain.PrizeStoredInDb
import com.example.domain.Ticket
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import java.util.*

@JdbcRepository(dialect = Dialect.POSTGRES)
interface PrizeRepository : CrudRepository<PrizeStoredInDb, UUID> {
    fun findAllByLotteryId(lotteryId: UUID): MutableList<PrizeStoredInDb?>
}