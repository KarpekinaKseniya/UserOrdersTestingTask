package com.self.education.service

import com.self.education.api.OrderResponse

interface OrdersService {

    fun findAllOrders(): List<OrderResponse>
}