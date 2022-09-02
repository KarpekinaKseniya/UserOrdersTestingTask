package com.self.education.service

import com.self.education.helper.mgGlynnEntity
import com.self.education.helper.mgGlynnResponse
import com.self.education.helper.ondrickaEntity
import com.self.education.helper.ondrickaResponse
import com.self.education.mapper.CompaniesMapper
import com.self.education.repository.CompaniesRepository
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.Mockito.only
import org.mockito.Mockito.times
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.MockitoAnnotations.openMocks
import kotlin.test.assertFailsWith

class CompaniesServiceTest {

    private val EXCEPTION: Exception = RuntimeException("some error message")

    @Mock
    private lateinit var companiesRepository: CompaniesRepository

    @Mock
    private lateinit var companiesMapper: CompaniesMapper

    private lateinit var service: CompaniesService

    @BeforeEach
    fun setUp() {
        openMocks(this)
        service = CompaniesServiceImpl(companiesRepository, companiesMapper)
    }

    @AfterEach
    fun tearDown() {
        verifyNoMoreInteractions(companiesMapper, companiesRepository)
    }

    @Test
    fun shouldFindAllCompaniesSuccess() {
        val expected = listOf(ondrickaResponse(), mgGlynnResponse())

        given(companiesRepository.findAll()).willReturn(listOf(ondrickaEntity(), mgGlynnEntity()))
        given(companiesMapper.mapToResponse(ondrickaEntity())).willReturn(ondrickaResponse())
        given(companiesMapper.mapToResponse(mgGlynnEntity())).willReturn(mgGlynnResponse())

        val actual = service.findAllCompanies()
        assertThat(actual, `is`(expected))

        then(companiesRepository).should(only()).findAll()
        then(companiesMapper).should(times(1)).mapToResponse(ondrickaEntity())
        then(companiesMapper).should(times(1)).mapToResponse(mgGlynnEntity())
    }

    @Test
    fun shouldFindAllCompaniesThrowExceptionWhenRepositoryReturnError() {
        given(companiesRepository.findAll()).willThrow(EXCEPTION)

        val actual = assertFailsWith<Exception>(block = { service.findAllCompanies() })
        assertThat(actual, `is`(EXCEPTION))

        then(companiesRepository).should(only()).findAll()
    }

    @Test
    fun shouldFindAllCompaniesThrowExceptionWhenMapperReturnError() {
        given(companiesRepository.findAll()).willReturn(listOf(ondrickaEntity(), mgGlynnEntity()))
        given(companiesMapper.mapToResponse(ondrickaEntity())).willThrow(EXCEPTION)

        val actual = assertFailsWith<Exception>(block = { service.findAllCompanies() })
        assertThat(actual, `is`(EXCEPTION))

        then(companiesRepository).should(only()).findAll()
        then(companiesMapper).should(only()).mapToResponse(ondrickaEntity())
    }
}