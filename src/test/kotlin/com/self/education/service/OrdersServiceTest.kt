package com.self.education.service

import com.self.education.helper.bfOrderEntity
import com.self.education.helper.bfOrderResponse
import com.self.education.helper.phOrderEntity
import com.self.education.helper.phOrderResponse
import com.self.education.mapper.OrdersMapper
import com.self.education.repository.OrdersRepository
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

class OrdersServiceTest {

    private val EXCEPTION: Exception = RuntimeException("Something went wrong")

    @Mock
    private lateinit var ordersRepository: OrdersRepository

    @Mock
    private lateinit var ordersMapper: OrdersMapper

    private lateinit var service: OrdersService

    @BeforeEach
    fun setUp() {
        openMocks(this)
        service = OrdersServiceImpl(ordersRepository, ordersMapper)
    }

    @AfterEach
    fun verify() {
        verifyNoMoreInteractions(ordersRepository, ordersMapper)
    }

    @Test
    fun shouldFindAllOrdersSuccess() {
        val expected = listOf(bfOrderResponse(), phOrderResponse())

        given(ordersRepository.findAll()).willReturn(listOf(bfOrderEntity(), phOrderEntity()))
        given(ordersMapper.mapToResponse(bfOrderEntity())).willReturn(bfOrderResponse())
        given(ordersMapper.mapToResponse(phOrderEntity())).willReturn(phOrderResponse())

        val actual = service.findAllOrders()
        assertThat(actual, `is`(expected))

        then(ordersRepository).should(only()).findAll()
        then(ordersMapper).should(times(1)).mapToResponse(bfOrderEntity())
        then(ordersMapper).should(times(1)).mapToResponse(phOrderEntity())
    }

    @Test
    fun shouldFindAllOrdersThrowsExceptionWhenRepositoryReturnError() {
        given(ordersRepository.findAll()).willThrow(EXCEPTION)

        val actual = assertFailsWith<Exception>(block = { service.findAllOrders() })
        assertThat(actual, `is`(EXCEPTION))

        then(ordersRepository).should(only()).findAll()
    }


    @Test
    fun shouldFindAllOrdersThrowsExceptionWhenMapperReturnError() {
        given(ordersRepository.findAll()).willReturn(listOf(bfOrderEntity(), phOrderEntity()))
        given(ordersMapper.mapToResponse(bfOrderEntity())).willThrow(EXCEPTION)

        val actual = assertFailsWith<Exception>(block = { service.findAllOrders() })
        assertThat(actual, `is`(EXCEPTION))

        then(ordersRepository).should(only()).findAll()
        then(ordersMapper).should(only()).mapToResponse(bfOrderEntity())
    }
}