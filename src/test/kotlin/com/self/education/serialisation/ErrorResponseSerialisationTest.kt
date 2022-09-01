package com.self.education.serialisation

import com.self.education.api.ErrorResponse
import org.junit.jupiter.api.BeforeEach
import java.util.function.Supplier
import javax.servlet.http.HttpServletResponse.SC_CONFLICT

class ErrorResponseSerialisationTest : JsonTestBase<ErrorResponse>() {

    @BeforeEach
    fun before() {
        expected = Supplier { ErrorResponse(SC_CONFLICT, "Error Message", "Error Description") }
        fileName = "expected_error_response.json"
        expectedType = ErrorResponse::class.java
    }
}