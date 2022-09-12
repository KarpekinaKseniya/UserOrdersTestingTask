package com.self.education.service

import com.self.education.api.UserResponse
import com.self.education.domain.User
import com.self.education.mapper.UsersMapper
import com.self.education.repository.UsersRepository
import org.springframework.stereotype.Service

@Service
class UsersServiceImpl(
    private val usersRepository: UsersRepository,
    private val usersMapper: UsersMapper
) : UsersService {

    override fun getAllUsers(): List<UserResponse> {
        return usersRepository.findAll().map { user: User -> usersMapper.mapToResponse(user) }
    }
}