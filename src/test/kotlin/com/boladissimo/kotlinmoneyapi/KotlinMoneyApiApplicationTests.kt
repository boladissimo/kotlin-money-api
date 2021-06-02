package com.boladissimo.kotlinmoneyapi

import org.cassandraunit.spring.CassandraDataSet
import org.cassandraunit.spring.CassandraUnitTestExecutionListener
import org.cassandraunit.spring.EmbeddedCassandra
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestExecutionListeners

@SpringBootTest
@EmbeddedCassandra
@CassandraDataSet(keyspace = "money_api", value = ["dataset.cql"])
@TestExecutionListeners(listeners = [CassandraUnitTestExecutionListener::class])
class KotlinMoneyApiApplicationTests {

	@Test
	fun contextLoads() {}

}
