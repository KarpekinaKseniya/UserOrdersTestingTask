package com.self.education.serialisation

import com.self.education.api.CompanyResponse
import com.self.education.helper.mgGlynnResponse
import org.junit.jupiter.api.BeforeEach
import java.util.function.Supplier

class CompanyResponseSerialisationTest : JsonTestBase<CompanyResponse>() {

    @BeforeEach
    fun before() {
        expected = Supplier { mgGlynnResponse() }
        fileName = "expected_company_response.json"
        expectedType = CompanyResponse::class.java
    }
}