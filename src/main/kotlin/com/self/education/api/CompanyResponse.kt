package com.self.education.api

import com.fasterxml.jackson.annotation.JsonProperty

data class CompanyResponse(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("title")
    val title: String,
    @JsonProperty("industry")
    val industry: String,
    @param:JsonProperty("market_cap") @get:JsonProperty("market_cap")
    val marketCup: String,
    @JsonProperty("sector")
    val sector: String,
    @JsonProperty("url")
    val url: String? = null
)
