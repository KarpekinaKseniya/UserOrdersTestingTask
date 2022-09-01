package com.self.education.mapper

import com.self.education.api.UserResponse
import com.self.education.domain.User
import com.self.education.helper.torrieEntity
import com.self.education.helper.torrieResponse
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class UsersMapperTest {
    private val mapper: UsersMapper = UsersMapperImpl()

    companion object {
        @JvmStatic
        fun data() = listOf(
            Arguments.of(null, null),
            Arguments.of(torrieEntity(), torrieResponse())
        )
    }

    @ParameterizedTest
    @MethodSource("data")
    fun shouldMapToResponse(entity: User?, response: UserResponse?) {
        MatcherAssert.assertThat(response, CoreMatchers.`is`(mapper.mapToResponse(entity)))
    }
}
