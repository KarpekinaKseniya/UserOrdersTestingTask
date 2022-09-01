package com.self.education.integration_tests

import com.self.education.helper.bfOrderEntity
import com.self.education.helper.phOrderEntity
import com.self.education.repository.OrdersRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = ["classpath:/application-test.properties"])
class OrdersResourceIT {

    @Autowired
    private lateinit var mvc: MockMvc

    @Autowired
    private lateinit var ordersRepository: OrdersRepository

    @BeforeEach
    fun setup() {
        ordersRepository.saveAll(listOf(phOrderEntity(), bfOrderEntity()))
    }

    @AfterEach
    fun clean() {
        ordersRepository.deleteAll()
    }

    @Test
    fun shouldGetAllOrdersSuccess() {
        mvc.perform(MockMvcRequestBuilders.get("/v1/orders"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(getResponse()))
    }

    @Throws(IOException::class)
    private fun getResponse(): String {
        return String(Files.readAllBytes(Paths.get("src/test/resources/integration/responses/get_all_orders_success.json")))
    }
}