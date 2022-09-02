package com.self.education.domain

import com.fasterxml.jackson.annotation.JsonValue

enum class Gender(@JsonValue val output: String) {
    MALE("Male"), FEMALE("Female")
}