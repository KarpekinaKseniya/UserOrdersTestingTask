package com.self.education.api

import com.fasterxml.jackson.annotation.JsonProperty
import com.self.education.domain.Gender

data class UserResponse(
    @JsonProperty("id")
    val id: Int,
    @param:JsonProperty("first_name") @get:JsonProperty("first_name")
    val firstName: String,
    @param:JsonProperty("last_name") @get:JsonProperty("last_name")
    val lastName: String,
    @JsonProperty("gender")
    val gender: Gender,
    @JsonProperty("birthday")
    val birthday: Double? = null,
    @JsonProperty("avatar")
    val avatar: String? = null,
    @param:JsonProperty("company_id") @get:JsonProperty("company_id")
    val companyId: Int? = null
)
