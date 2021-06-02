package com.boladissimo.kotlinmoneyapi.stocks.service

import com.boladissimo.kotlinmoneyapi.stocks.dto.request.StockCreateRequest
import com.boladissimo.kotlinmoneyapi.stocks.dto.request.StockUpdateRequest
import com.boladissimo.kotlinmoneyapi.stocks.entity.Stock

interface StockService {
    fun create(stockRequest: StockCreateRequest)
    fun findByCode(code: String): Stock
    fun findAll(): List<Stock>
    fun update(code: String, stockRequest: StockUpdateRequest)
    fun delete(code: String)
}