package com.example.domain

import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import java.util.UUID

@Serdeable
@MappedEntity
data class Lottery(
    @field:Id
    val id: UUID? = UUID.randomUUID(),
    val name: String,
    val tickets: Int? = 100
)

@Serdeable
data class LotteryWithPrizesInput(
    val name: String,
    val tickets: Int? = 100,
    val prizes: List<Prize>
)