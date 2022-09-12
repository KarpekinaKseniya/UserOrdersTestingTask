package com.self.education.repository

import com.self.education.helper.bfOrderEntity
import com.self.education.helper.phOrderEntity
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

@SpringBootTest
@TestPropertySource(locations = ["classpath:/application-test.properties"])
class OrdersRepositoryTest {

    @Autowired
    private lateinit var ordersRepository: OrdersRepository

    @BeforeEach
    fun before() {
        ordersRepository.saveAll(listOf(phOrderEntity(), bfOrderEntity()))
    }

    @AfterEach
    fun afterEach() {
        ordersRepository.deleteAll()
    }

    @Test
    fun shouldFindAllOrders() {
        val actual = ordersRepository.findAll()
        assertThat(actual, `is`(listOf(phOrderEntity(), bfOrderEntity())))
    }
}