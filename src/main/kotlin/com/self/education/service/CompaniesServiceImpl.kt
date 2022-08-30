package com.self.education.service

import com.self.education.api.CompanyResponse
import com.self.education.domain.Company
import com.self.education.mapper.CompaniesMapper
import com.self.education.repository.CompaniesRepository
import org.springframework.stereotype.Service

@Service
class CompaniesServiceImpl(
    private val companiesRepository: CompaniesRepository,
    private val companiesMapper: CompaniesMapper
) : CompaniesService {

    override fun findAllCompanies(): List<CompanyResponse> {
        return companiesRepository.findAll().map { company: Company -> companiesMapper.mapToResponse(company) }
    }
}