package com.self.education.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "orders")
data class Order(

    @Id
    @Field("_id")
    val primaryKey: ObjectId = ObjectId.get(),
    @Field("id")
    val id: Int,
    @Field("transaction_id")
    val transactionId: String,
    @Field("created_at")
    val createdAt: Double,
    @Field("user_id")
    val userId: Int,
    @Field("total")
    val total: Double,
    @Field("card_type")
    val cardType: String,
    @Field("card_number")
    val cardNumber: String,
    @Field("order_country")
    val orderCountry: String,
    @Field("order_ip")
    val orderIp: String
)