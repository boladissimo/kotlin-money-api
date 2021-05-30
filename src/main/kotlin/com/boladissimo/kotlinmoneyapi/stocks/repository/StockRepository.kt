package com.boladissimo.kotlinmoneyapi.stocks.repository

import com.boladissimo.kotlinmoneyapi.stocks.mapper.StockMapper
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StockRepository : CrudRepository<StockMapper, String>