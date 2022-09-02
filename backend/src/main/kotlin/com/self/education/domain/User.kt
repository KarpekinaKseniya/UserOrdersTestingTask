package com.self.education.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "users")
data class User(
    @Id
    @Field("_id")
    val primaryKey: ObjectId = ObjectId.get(),
    @Field("id")
    val id: Int,
    @Field("first_name")
    val firstName: String,
    @Field("last_name")
    val lastName: String,
    @Field("gender")
    val gender: Gender,
    @Field("birthday")
    val birthday: Double? = null,
    @Field("avatar")
    val avatar: String?,
    @Field("company_id")
    val companyId: Int? = null
)