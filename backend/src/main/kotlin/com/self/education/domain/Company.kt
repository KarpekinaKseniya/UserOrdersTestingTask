package com.self.education.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "companies")
data class Company (
    @Id
    @Field("_id")
    val primaryKey: ObjectId = ObjectId.get(),
    @Field("id")
    val id: Int,
    @Field("title")
    val title: String,
    @Field("industry")
    val industry: String,
    @Field("market_cap")
    val marketCup: String,
    @Field("sector")
    val sector: String,
    @Field("url")
    val url: String ?= null
)