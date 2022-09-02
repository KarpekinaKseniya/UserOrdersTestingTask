package com.self.education.api

import com.fasterxml.jackson.annotation.JsonProperty

data class OrderResponse(
    @JsonProperty("id")
    val id: Int,
    @param:JsonProperty("transaction_id") @get:JsonProperty("transaction_id")
    val transactionId: String,
    @param:JsonProperty("created_at") @get:JsonProperty("created_at")
    val createdAt: Double,
    @param:JsonProperty("user_id") @get:JsonProperty("user_id")
    val userId: Int,
    @JsonProperty("total")
    val total: Double,
    @param:JsonProperty("card_type") @get:JsonProperty("card_type")
    val cardType: String,
    @param:JsonProperty("card_number") @get:JsonProperty("card_number")
    val cardNumber: String,
    @param:JsonProperty("order_country") @get:JsonProperty("order_country")
    val orderCountry: String,
    @param:JsonProperty("order_ip") @get:JsonProperty("order_ip")
    val orderIp: String
)