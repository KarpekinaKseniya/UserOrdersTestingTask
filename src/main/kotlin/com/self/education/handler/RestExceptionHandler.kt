package com.self.education.handler

import com.self.education.api.ErrorResponse
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest


@RestControllerAdvice
open class RestExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(value = INTERNAL_SERVER_ERROR)
    open fun handleGlobalException(ex: Exception, request: WebRequest): ErrorResponse {
        //@formatter:off
        return ErrorResponse(
            INTERNAL_SERVER_ERROR.value(),
            ex.localizedMessage,
            request.getDescription(false)
        )
        //@formatter:on
    }
}