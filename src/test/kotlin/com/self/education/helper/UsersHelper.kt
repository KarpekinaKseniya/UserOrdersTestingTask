package com.self.education.helper

import com.self.education.api.UserResponse
import com.self.education.domain.Gender
import com.self.education.domain.User
import org.bson.types.ObjectId

private val TORRIE_PRIMARY_KEY: ObjectId = ObjectId.get()
private const val TORRIE_ID: Int = 4
private const val TORRIE_FIRST_NAME: String = "Torrie"
private const val TORRIE_LAST_NAME: String = "Mossman"
private const val TORRIE_BIRTHDAY: Double = 365721135.0
private const val TORRIE_AVATAR: String = "https://robohash.org/eaqueadipiscidistinctio.jpg?size=100x100"
private const val TORRIE_COMPANY_ID: Int = 5

private val BRIGITTE_PRIMARY_KEY: ObjectId = ObjectId.get()
private const val BRIGITTE_ID: Int = 19
private const val BRIGITTE_FIRST_NAME: String = "Brigitte"
private const val BRIGITTE_LAST_NAME: String = "Rimes"
private const val BRIGITTE_BIRTHDAY: Double = 604346704.0
private const val BRIGITTE_AVATAR: String = "https://robohash.org/ametveritatisqui.jpg?size=100x100"
private const val BRIGITTE_COMPANY_ID: Int = 5

fun torrieEntity(): User {
    return User(
        TORRIE_PRIMARY_KEY,
        TORRIE_ID,
        TORRIE_FIRST_NAME,
        TORRIE_LAST_NAME,
        Gender.FEMALE,
        TORRIE_BIRTHDAY,
        TORRIE_AVATAR,
        TORRIE_COMPANY_ID
    )
}

fun brigitteEntity(): User {
    return User(
        BRIGITTE_PRIMARY_KEY,
        BRIGITTE_ID,
        BRIGITTE_FIRST_NAME,
        BRIGITTE_LAST_NAME,
        Gender.FEMALE,
        BRIGITTE_BIRTHDAY,
        BRIGITTE_AVATAR,
        BRIGITTE_COMPANY_ID
    )
}

fun torrieResponse(): UserResponse {
    return UserResponse(
        TORRIE_ID,
        TORRIE_FIRST_NAME,
        TORRIE_LAST_NAME,
        Gender.FEMALE,
        TORRIE_BIRTHDAY,
        TORRIE_AVATAR,
        TORRIE_COMPANY_ID
    )
}

fun brigitteResponse(): UserResponse {
    return UserResponse(
        BRIGITTE_ID,
        BRIGITTE_FIRST_NAME,
        BRIGITTE_LAST_NAME,
        Gender.FEMALE,
        BRIGITTE_BIRTHDAY,
        BRIGITTE_AVATAR,
        BRIGITTE_COMPANY_ID
    )
}
