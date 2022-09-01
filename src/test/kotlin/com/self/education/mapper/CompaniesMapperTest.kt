package com.self.education.mapper

import com.self.education.api.CompanyResponse
import com.self.education.domain.Company
import com.self.education.helper.mgGlynnEntity
import com.self.education.helper.mgGlynnResponse
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class CompaniesMapperTest {

    private val mapper: CompaniesMapper = CompaniesMapperImpl()

    companion object {
        @JvmStatic
        fun data() = listOf(
            Arguments.of(null, null),
            Arguments.of(mgGlynnEntity(), mgGlynnResponse())
        )
    }

    @ParameterizedTest
    @MethodSource("data")
    fun shouldMapToResponse(entity: Company?, response: CompanyResponse?) {
        assertThat(response, `is`(mapper.mapToResponse(entity)))
    }
}