package com.self.education.helper

import com.self.education.api.CompanyResponse
import com.self.education.domain.Company
import org.bson.types.ObjectId

private val ONDRICKA_PRIMARY_KEY: ObjectId = ObjectId.get()
private const val ONDRICKA_ID: Int = 4
private const val ONDRICKA_TITLE: String = "Ondricka Inc"
private const val ONDRICKA_INDUSTRY: String = "Major Pharmaceuticals"
private const val ONDRICKA_MARKET_CAP: String = "$681.44M"
private const val ONDRICKA_SECTOR: String = "Health Care"
private const val ONDRICKA_URL: String = "https://naver.com"
private val MG_GLYNN_PRIMARY_KEY: ObjectId = ObjectId.get()
private const val MG_GLYNN_ID: Int = 7
private const val MG_GLYNN_TITLE: String = "McGlynn LLC"
private const val MG_GLYNN_INDUSTRY: String = "n/a"
private const val MG_GLYNN_MARKET_CAP: String = "n/a"
private const val MG_GLYNN_SECTOR: String = "n/a"
private const val MG_GLYNN_URL: String = "http://tamu.edu"

fun ondrickaEntity(): Company {
    return Company(
        ONDRICKA_PRIMARY_KEY,
        ONDRICKA_ID,
        ONDRICKA_TITLE,
        ONDRICKA_INDUSTRY,
        ONDRICKA_MARKET_CAP,
        ONDRICKA_SECTOR,
        ONDRICKA_URL
    )
}

fun mgGlynnEntity(): Company {
    return Company(
        MG_GLYNN_PRIMARY_KEY,
        MG_GLYNN_ID,
        MG_GLYNN_TITLE,
        MG_GLYNN_INDUSTRY,
        MG_GLYNN_MARKET_CAP,
        MG_GLYNN_SECTOR,
        MG_GLYNN_URL
    )
}

fun ondrickaResponse(): CompanyResponse {
    return CompanyResponse(
        ONDRICKA_ID,
        ONDRICKA_TITLE,
        ONDRICKA_INDUSTRY,
        ONDRICKA_MARKET_CAP,
        ONDRICKA_SECTOR,
        ONDRICKA_URL
    )
}

fun mgGlynnResponse(): CompanyResponse {
    return CompanyResponse(
        MG_GLYNN_ID,
        MG_GLYNN_TITLE,
        MG_GLYNN_INDUSTRY,
        MG_GLYNN_MARKET_CAP,
        MG_GLYNN_SECTOR,
        MG_GLYNN_URL
    )
}
