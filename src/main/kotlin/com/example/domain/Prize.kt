package com.example.domain

import com.fasterxml.jackson.databind.InjectableValues
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import java.util.UUID

@Serdeable
data class Prize(
    val name: String,
    val cost: Double
)

@Serdeable
@MappedEntity
data class PrizeStoredInDb(
    @field:Id
    val id: UUID? = UUID.randomUUID(),
    val name: String,
    val cost: Double,
    val stillAvailable: Boolean,
    val lotteryId: UUID
)
