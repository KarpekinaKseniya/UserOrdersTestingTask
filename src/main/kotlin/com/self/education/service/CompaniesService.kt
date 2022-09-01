package com.self.education.service

import com.self.education.api.CompanyResponse

interface CompaniesService {

    fun findAllCompanies(): List<CompanyResponse>
}