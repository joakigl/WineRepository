package com.example.repository

import com.example.domain.Lottery
import com.example.initializeDatabaseSchema
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

@MicronautTest
class LotteryRepositoryTest {
    @Inject
    lateinit var lotteryRepository: LotteryRepository

    private val id = UUID.randomUUID()

    private val lottery = Lottery(
        id = id,
        name = "lottery 1",
        tickets = 100
    )

    @BeforeEach
    fun setup() {
        initializeDatabaseSchema()
        lotteryRepository.save(lottery)
    }

    @Test
    fun `Should retrieve a Lottery by Id`() {
        val result = lotteryRepository.getById(id)

        Assertions.assertEquals(lottery, result)
    }
}