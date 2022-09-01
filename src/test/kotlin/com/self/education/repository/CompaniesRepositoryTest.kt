package com.self.education.repository

import com.self.education.helper.mgGlynnEntity
import com.self.education.helper.ondrickaEntity
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
class CompaniesRepositoryTest {

    @Autowired
    private lateinit var companiesRepository: CompaniesRepository

    @BeforeEach
    fun before() {
        companiesRepository.saveAll(listOf(mgGlynnEntity(), ondrickaEntity()))
    }

    @AfterEach
    fun after() {
        companiesRepository.deleteAll()
    }

    @Test
    fun shouldFindAllCompanies() {

        val actual = companiesRepository.findAll()
        assertThat(actual, `is`(listOf(mgGlynnEntity(), ondrickaEntity())))
    }
}