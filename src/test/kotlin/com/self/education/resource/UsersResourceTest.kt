package com.self.education.resource

import com.self.education.helper.brigitteResponse
import com.self.education.helper.torrieResponse
import com.self.education.service.UsersService
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.Mockito.only
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.MockitoAnnotations.openMocks
import org.springframework.http.ResponseEntity.ok
import kotlin.test.assertFailsWith

class UsersResourceTest {

    @Mock
    private lateinit var usersService: UsersService

    private lateinit var resource: UsersResource

    @BeforeEach
    fun setUp() {
        openMocks(this)
        resource = UsersResource(usersService)
    }

    @AfterEach
    fun verify() {
        verifyNoMoreInteractions(usersService)
    }

    @Test
    fun shouldGetAllUsersSuccess() {
        val expected = listOf(torrieResponse(), brigitteResponse())

        given(usersService.getAllUsers()).willReturn(expected)

        val actual = resource.getAllUsers()
        assertThat(actual, `is`(ok(expected)))

        then(usersService).should(only()).getAllUsers()
    }

    @Test
    fun shouldGetAllUsersThrowsWhenServiceReturnException() {
        val exception: Exception = RuntimeException("ERROR!!!")

        given(usersService.getAllUsers()).willThrow(exception)

        val actual = assertFailsWith<Exception> { resource.getAllUsers() }
        assertThat(actual, `is`(exception))

        then(usersService).should(only()).getAllUsers()
    }
}