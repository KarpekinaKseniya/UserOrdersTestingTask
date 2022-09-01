package com.self.education.serialisation

import com.self.education.api.UserResponse
import com.self.education.helper.torrieResponse
import org.junit.jupiter.api.BeforeEach
import java.util.function.Supplier

class UserResponseSerialisationTest : JsonTestBase<UserResponse>() {

    @BeforeEach
    fun before() {
        expected = Supplier { torrieResponse() }
        expectedType = UserResponse::class.java
        fileName = "expected_user_response.json"
    }
}