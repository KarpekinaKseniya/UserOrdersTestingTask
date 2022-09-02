package com.self.education.handler

import com.self.education.api.ErrorResponse
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.only
import org.mockito.BDDMockito.then
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations.openMocks
import org.springframework.web.context.request.WebRequest
import javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR

private const val DESCRIPTION: String = "Some description"
private const val ERROR_MESSAGE: String = "Error message"

class RestExceptionHandlerTest {

    @Mock
    private lateinit var webRequest: WebRequest

    @InjectMocks
    private lateinit var handler: RestExceptionHandler

    @BeforeEach
    fun setup() {
        openMocks(this)
        given(webRequest.getDescription(false)).willReturn(DESCRIPTION)
    }

    @AfterEach
    fun verify() {
        then(webRequest).should(only()).getDescription(false)
    }

    @Test
    fun shouldHandleGlobalException() {
        val exception: Exception = RuntimeException(ERROR_MESSAGE)
        val actual: ErrorResponse = handler.handleGlobalException(exception, webRequest)
        assertThat(actual, `is`(ErrorResponse(SC_INTERNAL_SERVER_ERROR, ERROR_MESSAGE, DESCRIPTION)))
    }
}