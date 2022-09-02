package com.self.education.repository

import com.self.education.domain.Company
import org.springframework.data.mongodb.repository.MongoRepository

interface CompaniesRepository : MongoRepository<Company, String>