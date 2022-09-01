package com.self.education.service

import com.self.education.api.UserResponse

interface UsersService {

    fun getAllUsers(): List<UserResponse>
}