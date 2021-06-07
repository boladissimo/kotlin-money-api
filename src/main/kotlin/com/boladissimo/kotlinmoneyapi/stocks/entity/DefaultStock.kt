package com.boladissimo.kotlinmoneyapi.stocks.entity

data class DefaultStock(
    var code: String,
    var fantasyName: String
    ) : Stock {

    override fun code(): String {
        return code
    }

    override fun fantasyName(): String {
        return fantasyName
    }
}