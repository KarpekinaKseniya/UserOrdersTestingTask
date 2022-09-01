package com.self.education.repository

import com.self.education.helper.brigitteEntity
import com.self.education.helper.torrieEntity
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

@SpringBootTest
@TestPropertySource(locations = ["classpath:/application-test.properties"])
class UsersRepositoryTest {

    @Autowired
    private lateinit var usersRepository: UsersRepository

    @BeforeEach
    fun before() {
        usersRepository.saveAll(listOf(torrieEntity(), brigitteEntity()))
    }

    @AfterEach
    fun after() {
        usersRepository.deleteAll()
    }

    @Test
    fun shouldFindAllUsers() {
        val actual = usersRepository.findAll()
        assertThat(actual, `is`(listOf(torrieEntity(), brigitteEntity())))
    }
}