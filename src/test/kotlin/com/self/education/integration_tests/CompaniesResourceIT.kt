package com.self.education.integration_tests

import com.self.education.helper.mgGlynnEntity
import com.self.education.helper.ondrickaEntity
import com.self.education.repository.CompaniesRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = ["classpath:/application-test.properties"])
class CompaniesResourceIT {

    @Autowired
    private lateinit var mvc: MockMvc

    @Autowired
    private lateinit var companiesRepository: CompaniesRepository

    @BeforeEach
    fun setup() {
        companiesRepository.saveAll(listOf(mgGlynnEntity(), ondrickaEntity()))
    }

    @AfterEach
    fun clean() {
        companiesRepository.deleteAll()
    }

    @Test
    fun shouldShowAllCompanies() {
        mvc.perform(get("/v1/companies"))
            .andExpect(status().isOk)
            .andExpect(content().json(getResponse()))
    }

    @Throws(IOException::class)
    private fun getResponse(): String {
        return String(Files.readAllBytes(Paths.get("src/test/resources/integration/responses/get_all_companies_success.json")))
    }
}