package com.boladissimo.kotlinmoneyapi.stocks.service

import com.boladissimo.kotlinmoneyapi.stocks.dto.request.StockCreateRequest
import com.boladissimo.kotlinmoneyapi.stocks.dto.request.StockUpdateRequest
import com.boladissimo.kotlinmoneyapi.stocks.entity.DefaultStock
import com.boladissimo.kotlinmoneyapi.stocks.entity.Stock
import com.boladissimo.kotlinmoneyapi.stocks.mapper.StockMapper
import com.boladissimo.kotlinmoneyapi.stocks.repository.StockRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class StockServiceImpl(
    private val stockRepository: StockRepository
    ) : StockService {

    override fun create(stockRequest: StockCreateRequest) {
        validateStockDontExists(stockRequest.code)
        stockRepository.save(StockMapper(stockRequest.code, stockRequest.fantasyName))
    }

    override fun findByCode(code: String): Stock {
        val stockMapper = stockRepository.findById(code)
        return toEntity(stockMapper.orElseThrow {ResponseStatusException(HttpStatus.NOT_FOUND, "Stock with code $code not found")})
    }

    override fun findAll(): List<Stock> {
        return stockRepository.findAll().map { stockMapper -> toEntity(stockMapper) }.toList()
    }

    override fun update(code: String, stockRequest: StockUpdateRequest) {
        validateStockExists(code)
        stockRepository.save(StockMapper(code, stockRequest.fantasyName))
    }

    override fun delete(code: String) {
        validateStockExists(code)
        stockRepository.deleteById(code)
    }

    private fun toEntity(mapper: StockMapper) : DefaultStock {
        return DefaultStock(mapper.code, mapper.fantasyName)
    }

    private fun validateStockExists(code: String) {
        if (!stockRepository.existsById(code)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Stock with code $code not found")
        }
    }

    private fun validateStockDontExists(code: String) {
        if (stockRepository.existsById(code)) {
            throw ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Stock with code $code already found")
        }
    }
}