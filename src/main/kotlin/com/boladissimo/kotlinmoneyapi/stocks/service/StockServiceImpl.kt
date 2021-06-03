package com.boladissimo.kotlinmoneyapi.stocks.service

import com.boladissimo.kotlinmoneyapi.stocks.dto.request.StockCreateRequest
import com.boladissimo.kotlinmoneyapi.stocks.dto.request.StockUpdateRequest
import com.boladissimo.kotlinmoneyapi.stocks.entity.DefaultStock
import com.boladissimo.kotlinmoneyapi.stocks.entity.Stock
import com.boladissimo.kotlinmoneyapi.stocks.mapper.StockMapper
import com.boladissimo.kotlinmoneyapi.stocks.repository.StockRepository
import org.springframework.stereotype.Service

@Service
class StockServiceImpl(
    private val stockRepository: StockRepository
    ) : StockService {

    override fun create(stockRequest: StockCreateRequest) {
        stockRepository.save(StockMapper(stockRequest.code, stockRequest.fantasyName))
    }

    override fun findByCode(code: String): Stock {
        val stockMapper = stockRepository.findById(code)
        return toEntity(stockMapper.orElseThrow()) //TODO: 404 exception
    }

    override fun findAll(): List<Stock> {
        return stockRepository.findAll().map { stockMapper -> toEntity(stockMapper) }.toList()
    }

    override fun update(code: String, stockRequest: StockUpdateRequest) {
        if (stockRepository.existsById(code)) {
            stockRepository.save(StockMapper(code, stockRequest.fantasyName))
        } else {
            throw java.lang.Exception() //TODO: 404 exception
        }
    }

    override fun delete(code: String) {
        if (stockRepository.existsById(code)) {
            stockRepository.deleteById(code)
        } else {
            throw java.lang.Exception() //TODO: 404 exception
        }
    }

    private fun toEntity(mapper: StockMapper) : DefaultStock {
        return DefaultStock(mapper.code, mapper.fantasyName)
    }
}