package com.self.education.repository

import com.self.education.domain.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UsersRepository : MongoRepository<User, String>