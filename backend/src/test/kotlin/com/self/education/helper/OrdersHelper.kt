package com.self.education.helper

import com.self.education.api.OrderResponse
import com.self.education.domain.Order
import org.bson.types.ObjectId

private const val CARD_TYPE: String = "maestro"

private val PH_PRIMARY_KEY: ObjectId = ObjectId.get()
private const val PH_ID: Int = 21
private const val PH_TRANSACTION_ID: String = "d86eb74d-b59b-4c21-bc02-68608c18ce9f"
private const val PH_CREATED_AT: Double = 1474139904.0
private const val PH_USER_ID: Int = 4
private const val PH_TOTAL: Double = 1073.39
private const val PH_CARD_NUMBER: String = "6762303363715056"
private const val PH_ORDER_COUNTRY: String = "PH"
private const val PH_ORDER_IP: String = "233.84.21.1"

private val BF_PRIMARY_KEY: ObjectId = ObjectId.get()
private const val BF_ID: Int = 149
private const val BF_TRANSACTION_ID: String = "085301d8-54e2-44b3-ac34-c53e70452457"
private const val BF_CREATED_AT: Double = 1491898957.0
private const val BF_USER_ID: Int = 20
private const val BF_TOTAL: Double = 112.15
private const val BF_CARD_NUMBER: String = "6762380055750377"
private const val BF_ORDER_COUNTRY: String = "BF"
private const val BF_ORDER_IP = "60.136.221.10"


fun phOrderEntity(): Order {
    return Order(
        PH_PRIMARY_KEY,
        PH_ID,
        PH_TRANSACTION_ID,
        PH_CREATED_AT,
        PH_USER_ID,
        PH_TOTAL,
        CARD_TYPE,
        PH_CARD_NUMBER,
        PH_ORDER_COUNTRY,
        PH_ORDER_IP
    )
}

fun bfOrderEntity(): Order {
    return Order(
        BF_PRIMARY_KEY,
        BF_ID,
        BF_TRANSACTION_ID,
        BF_CREATED_AT,
        BF_USER_ID,
        BF_TOTAL,
        CARD_TYPE,
        BF_CARD_NUMBER,
        BF_ORDER_COUNTRY,
        BF_ORDER_IP
    )
}

fun phOrderResponse(): OrderResponse {
    return OrderResponse(
        PH_ID,
        PH_TRANSACTION_ID,
        PH_CREATED_AT,
        PH_USER_ID,
        PH_TOTAL,
        CARD_TYPE,
        PH_CARD_NUMBER,
        PH_ORDER_COUNTRY,
        PH_ORDER_IP
    )
}

fun bfOrderResponse(): OrderResponse {
    return OrderResponse(
        BF_ID,
        BF_TRANSACTION_ID,
        BF_CREATED_AT,
        BF_USER_ID,
        BF_TOTAL,
        CARD_TYPE,
        BF_CARD_NUMBER,
        BF_ORDER_COUNTRY,
        BF_ORDER_IP
    )
}