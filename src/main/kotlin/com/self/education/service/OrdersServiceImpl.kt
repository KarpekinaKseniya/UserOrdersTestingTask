package com.self.education.service

import com.self.education.api.OrderResponse
import com.self.education.domain.Order
import com.self.education.mapper.OrdersMapper
import com.self.education.repository.OrdersRepository
import org.springframework.stereotype.Service

@Service
class OrdersServiceImpl(
    private val ordersRepository: OrdersRepository,
    private val ordersMapper: OrdersMapper
) : OrdersService {

    override fun findAllOrders(): List<OrderResponse> {
        return ordersRepository.findAll().map { order: Order -> ordersMapper.mapToResponse(order) }
    }
}