package com.self.education.service

import com.self.education.helper.brigitteEntity
import com.self.education.helper.brigitteResponse
import com.self.education.helper.torrieEntity
import com.self.education.helper.torrieResponse
import com.self.education.mapper.UsersMapper
import com.self.education.repository.UsersRepository
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.Mockito.only
import org.mockito.Mockito.times
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.MockitoAnnotations.openMocks
import kotlin.test.assertFailsWith

class UsersServiceTest {

    private val EXCEPTION: Exception = RuntimeException("Some Error Message")

    @Mock
    private lateinit var usersRepository: UsersRepository

    @Mock
    private lateinit var usersMapper: UsersMapper

    private lateinit var service: UsersService

    @BeforeEach
    fun setUp() {
        openMocks(this)
        service = UsersServiceImpl(usersRepository, usersMapper)
    }

    @AfterEach
    fun verify() {
        verifyNoMoreInteractions(usersRepository, usersMapper)
    }

    @Test
    fun shouldGetAllUsersSuccess() {
        val expected = listOf(torrieResponse(), brigitteResponse())
        given(usersRepository.findAll()).willReturn(listOf(torrieEntity(), brigitteEntity()))
        given(usersMapper.mapToResponse(torrieEntity())).willReturn(torrieResponse())
        given(usersMapper.mapToResponse(brigitteEntity())).willReturn(brigitteResponse())

        val actual = service.getAllUsers()
        assertThat(actual, `is`(expected))

        then(usersRepository).should(only()).findAll()
        then(usersMapper).should(times(1)).mapToResponse(torrieEntity())
        then(usersMapper).should(times(1)).mapToResponse(brigitteEntity())
    }

    @Test
    fun shouldGetAllUsersThrowsExceptionWhenRepositoryReturnError() {
        given(usersRepository.findAll()).willThrow(EXCEPTION)

        val actual = assertFailsWith<Exception> { service.getAllUsers() }
        assertThat(actual, `is`(EXCEPTION))

        then(usersRepository).should(only()).findAll()
    }

    @Test
    fun shouldGetAllUsersThrowsExceptionWhenMapperReturnError() {
        given(usersRepository.findAll()).willReturn(listOf(brigitteEntity(), torrieEntity()))
        given(usersMapper.mapToResponse(brigitteEntity())).willThrow(EXCEPTION)

        val actual = assertFailsWith<Exception> { service.getAllUsers() }
        assertThat(actual, `is`(EXCEPTION))

        then(usersRepository).should(only()).findAll()
        then(usersMapper).should(only()).mapToResponse(brigitteEntity())
    }
}