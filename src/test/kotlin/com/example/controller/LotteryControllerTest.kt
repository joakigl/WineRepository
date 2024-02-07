package com.example.controller

import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.MediaType
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Test

@MicronautTest
class LotteryControllerTest {

    @Inject
    @field:Client("/lottery")
    lateinit var httpClient: HttpClient

    @Test
    fun `should create a default lottery`() {
        val requestBody = """{"name": "testName"}"""

        val request = HttpRequest.POST("/createNew",requestBody)
            .contentType(MediaType.APPLICATION_JSON_TYPE)

        val response = httpClient.toBlocking().exchange(request, Argument.STRING, Argument.STRING)

        println(response.body())
    }

    @Test
    fun `should create a lottery with more tickets`() {
        val requestBody = """{"name": "testName", "tickets": 250}"""

        val request = HttpRequest.POST("/createNew",requestBody)
            .contentType(MediaType.APPLICATION_JSON_TYPE)

        val response = httpClient.toBlocking().exchange(request, Argument.STRING, Argument.STRING)

        println(response.body())
    }
}