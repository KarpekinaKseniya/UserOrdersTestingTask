package com.self.education.serialisation

import com.self.education.api.OrderResponse
import com.self.education.helper.phOrderResponse
import org.junit.jupiter.api.BeforeEach
import java.util.function.Supplier

class OrderResponseSerialisationTest : JsonTestBase<OrderResponse>() {

    @BeforeEach
    fun before() {
        expected = Supplier { phOrderResponse() }
        expectedType = OrderResponse::class.java
        fileName = "expected_order_response.json"
    }
}