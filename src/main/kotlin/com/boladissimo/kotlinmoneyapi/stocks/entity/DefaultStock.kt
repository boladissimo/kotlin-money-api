package com.boladissimo.kotlinmoneyapi.stocks.entity

data class DefaultStock(
    private var code: String,
    private var fantasyName: String
    ) : Stock {

    override fun code(): String {
        return code
    }

    override fun fantasyName(): String {
        return fantasyName
    }
}