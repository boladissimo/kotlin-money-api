package com.boladissimo.kotlinmoneyapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories

@SpringBootApplication
@EnableCassandraRepositories
class KotlinMoneyApiApplication

fun main(args: Array<String>) {
	runApplication<KotlinMoneyApiApplication>(*args)
}
