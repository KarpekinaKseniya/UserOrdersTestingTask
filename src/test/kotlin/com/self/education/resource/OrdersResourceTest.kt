package com.self.education.resource

import com.self.education.helper.bfOrderResponse
import com.self.education.helper.phOrderResponse
import com.self.education.service.OrdersService
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

class OrdersResourceTest {

    @Mock
    private lateinit var ordersService: OrdersService

    private lateinit var resource: OrdersResource

    @BeforeEach
    fun setUp() {
        openMocks(this)
        resource = OrdersResource(ordersService)
    }

    @AfterEach
    fun verify() {
        verifyNoMoreInteractions(ordersService)
    }

    @Test
    fun shouldGetAllOrdersSuccess() {
        val expected = listOf(phOrderResponse(), bfOrderResponse())

        given(ordersService.findAllOrders()).willReturn(expected)

        val actual = resource.getAllOrders()
        assertThat(actual, `is`(ok(expected)))

        then(ordersService).should(only()).findAllOrders()
    }

    @Test
    fun shouldGetAllOrdersThrowsWhenServiceReturnException() {
        val exception: Exception = RuntimeException("some error message")

        given(ordersService.findAllOrders()).willThrow(exception)

        val actual = assertFailsWith<Exception> { resource.getAllOrders() }
        assertThat(actual, `is`(exception))

        then(ordersService).should(only()).findAllOrders()
    }
}