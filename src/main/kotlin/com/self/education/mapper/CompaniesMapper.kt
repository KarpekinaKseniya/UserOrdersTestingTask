package com.self.education.mapper

import com.self.education.api.CompanyResponse
import com.self.education.domain.Company
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface CompaniesMapper {

    fun mapToResponse(company: Company?): CompanyResponse
}