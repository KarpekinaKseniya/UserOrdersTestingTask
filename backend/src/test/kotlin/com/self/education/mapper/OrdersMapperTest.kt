package com.self.education.mapper

import com.self.education.api.OrderResponse
import com.self.education.domain.Order
import com.self.education.helper.phOrderEntity
import com.self.education.helper.phOrderResponse
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class OrdersMapperTest {
    private val mapper: OrdersMapper = OrdersMapperImpl()

    companion object {
        @JvmStatic
        fun data() = listOf(
            Arguments.of(null, null),
            Arguments.of(phOrderEntity(), phOrderResponse())
        )
    }

    @ParameterizedTest
    @MethodSource("data")
    fun shouldMapToResponse(entity: Order?, response: OrderResponse?) {
        MatcherAssert.assertThat(response, CoreMatchers.`is`(mapper.mapToResponse(entity)))
    }
}