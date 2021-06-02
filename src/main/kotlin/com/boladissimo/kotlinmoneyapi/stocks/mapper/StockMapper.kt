package com.boladissimo.kotlinmoneyapi.stocks.mapper

import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table

@Table("stock")
data class StockMapper(
    @PrimaryKey
    var code: String,
    var fantasyName: String
)