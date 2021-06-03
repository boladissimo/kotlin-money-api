package com.boladissimo.kotlinmoneyapi.stocks.service

import com.boladissimo.kotlinmoneyapi.stocks.dto.request.StockCreateRequest
import com.boladissimo.kotlinmoneyapi.stocks.dto.request.StockUpdateRequest
import com.boladissimo.kotlinmoneyapi.stocks.entity.DefaultStock
import com.boladissimo.kotlinmoneyapi.stocks.mapper.StockMapper
import com.boladissimo.kotlinmoneyapi.stocks.repository.StockRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.lang.Exception
import java.util.*

class StockServiceTest {

    private val repositoryMock: StockRepository = Mockito.mock(StockRepository::class.java)
    private val service: StockService = StockServiceImpl(repositoryMock)

    private val teslaCode = "TSLA34"
    private val teslaFantasyName = "Tesla Inc"
    private val expectedSavedStock = StockMapper(teslaCode, teslaFantasyName)

    @Test
    fun `create, assert call save from repository with given values`() {
        val createRequest = StockCreateRequest(teslaCode, teslaFantasyName)

        service.create(createRequest)

        Mockito.verify(repositoryMock).save(expectedSavedStock)
    }

    @Test
    fun `findByCode, assert that given existent code return stock entity`() {
        Mockito.`when`(repositoryMock.findById(teslaCode)).thenReturn(Optional.of(expectedSavedStock))

        val actual = service.findByCode(teslaCode)

        assertEquals(teslaCode, actual.code())
        assertEquals(teslaFantasyName, actual.fantasyName())

    }

    @Test
    fun `findByCode, assert throw exception given non existent code`() {
        Mockito.`when`(repositoryMock.findById(teslaCode)).thenReturn(Optional.empty())
        assertThrows(Exception::class.java) { service.findByCode(teslaCode) }
    }

    @Test
    fun `findAll, assert call findAll from repository, returning stock entity list`() {
        Mockito.`when`(repositoryMock.findAll()).thenReturn(listOf(expectedSavedStock))
        val expectedResult = listOf(DefaultStock(teslaCode, teslaFantasyName))

        val actual = service.findAll()

        assertTrue(actual.containsAll(expectedResult))
    }

    @Test
    fun `update, assert call save from repository given existent code`() {
        Mockito.`when`(repositoryMock.existsById(teslaCode)).thenReturn(true)

        service.update(teslaCode, StockUpdateRequest(teslaFantasyName))

        Mockito.verify(repositoryMock).save(expectedSavedStock)
    }

    @Test
    fun `update, assert throw exception given non existent code`() {
        Mockito.`when`(repositoryMock.existsById(teslaCode)).thenReturn(false)

        assertThrows(Exception::class.java) { service.update(teslaCode, StockUpdateRequest(teslaFantasyName)) }
    }

    @Test
    fun `delete, assert call deleteById from repository given existent code`() {
        Mockito.`when`(repositoryMock.existsById(teslaCode)).thenReturn(true)

        service.delete(teslaCode)

        Mockito.verify(repositoryMock).deleteById(teslaCode)
    }

    @Test
    fun `delete, assert throw exception given non existent code`() {
        Mockito.`when`(repositoryMock.existsById(teslaCode)).thenReturn(false)

        assertThrows(Exception::class.java) { service.delete(teslaCode) }
    }

}