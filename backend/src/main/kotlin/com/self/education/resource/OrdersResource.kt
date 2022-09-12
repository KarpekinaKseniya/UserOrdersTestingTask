package com.self.education.resource

import com.self.education.api.ErrorResponse
import com.self.education.api.OrderResponse
import com.self.education.service.OrdersService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/orders")
class OrdersResource(private val ordersService: OrdersService) {

    //@formatter:off
    @Operation(summary = "Get all orders",
        description = "Endpoint for getting all orders", responses = [
            ApiResponse(responseCode = "200", description = "Ok"),
            ApiResponse(responseCode = "400", description = "Bad Request", content = [Content(schema = Schema(implementation = ErrorResponse::class))]),
            ApiResponse(responseCode = "500", description = "Internal Server Error", content = [Content(schema = Schema(implementation = ErrorResponse::class))])
        ])
    //@formatter:on
    @GetMapping
    fun getAllOrders(): ResponseEntity<List<OrderResponse>> {
        return ResponseEntity.ok(ordersService.findAllOrders())
    }
}