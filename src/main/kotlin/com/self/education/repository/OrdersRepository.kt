package com.self.education.repository

import com.self.education.domain.Order
import org.springframework.data.mongodb.repository.MongoRepository

interface OrdersRepository : MongoRepository<Order, String>