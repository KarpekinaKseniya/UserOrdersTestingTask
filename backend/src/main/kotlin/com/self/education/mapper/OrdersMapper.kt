package com.self.education.mapper

import com.self.education.api.OrderResponse
import com.self.education.domain.Order
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface OrdersMapper {

    fun mapToResponse(order: Order?): OrderResponse
}