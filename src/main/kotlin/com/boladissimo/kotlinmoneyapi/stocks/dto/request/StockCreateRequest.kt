package com.boladissimo.kotlinmoneyapi.stocks.dto.request

data class StockCreateRequest(
    val code: String,
    val fantasyName: String
)