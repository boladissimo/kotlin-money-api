package com.boladissimo.kotlinmoneyapi.stocks.service

import com.boladissimo.kotlinmoneyapi.stocks.dto.request.StockCreateRequest
import com.boladissimo.kotlinmoneyapi.stocks.mapper.StockMapper
import com.boladissimo.kotlinmoneyapi.stocks.repository.StockRepository
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class StockServiceTest {

    private val repositoryMock : StockRepository = Mockito.mock(StockRepository::class.java)
    private val service : StockService = StockServiceImpl(repositoryMock)

    private val teslaCode = "TSLA34"
    private val teslaFantasyName = "Tesla Inc"

    @Test
    fun `assert call save from repository with given values`() {
        val createRequest = StockCreateRequest(teslaCode, teslaFantasyName)
        val expectedSavedStock = StockMapper(teslaCode, teslaFantasyName)
        service.create(createRequest)

        Mockito.verify(repositoryMock).save(expectedSavedStock)
    }
}