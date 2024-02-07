package com.example.domain

import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import java.util.*

@Serdeable
@MappedEntity
data class Ticket(
    @field:Id
    val id: UUID? = UUID.randomUUID(),
    val number: Int,
    val availableToPurchase: Boolean,
    val lotteryId: UUID
)
