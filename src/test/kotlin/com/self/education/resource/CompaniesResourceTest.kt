package com.self.education.resource

import com.self.education.helper.mgGlynnResponse
import com.self.education.helper.ondrickaResponse
import com.self.education.service.CompaniesService
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.Mockito.only
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.MockitoAnnotations.openMocks
import org.springframework.http.ResponseEntity.ok
import kotlin.test.assertFailsWith

class CompaniesResourceTest {

    @Mock
    private lateinit var companiesService: CompaniesService

    private lateinit var resource: CompaniesResource

    @BeforeEach
    fun setup() {
        openMocks(this)
        resource = CompaniesResource(companiesService)
    }

    @AfterEach
    fun verify() {
        verifyNoMoreInteractions(companiesService)
    }

    @Test
    fun shouldGetAllCompaniesSuccess() {
        val expected = listOf(ondrickaResponse(), mgGlynnResponse())

        given(companiesService.findAllCompanies()).willReturn(expected)

        val actual = resource.getAllCompanies()
        assertThat(actual, `is`(ok(expected)))

        then(companiesService).should(only()).findAllCompanies()
    }

    @Test
    fun shouldGetAllCompaniesThrowExceptionWhenServiceReturnError() {
        val exception: Exception = RuntimeException("error message")

        given(companiesService.findAllCompanies()).willThrow(exception)

        val actual =  assertFailsWith<Exception>(block = { resource.getAllCompanies() })
        assertThat(actual, `is`(exception))

        then(companiesService).should(only()).findAllCompanies()
    }
}