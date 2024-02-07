package com.example.repository

import com.example.domain.Lottery
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import java.util.*

@JdbcRepository(dialect = Dialect.POSTGRES)
interface LotteryRepository : CrudRepository<Lottery, UUID> {
    fun getById(id: UUID): Lottery?
}