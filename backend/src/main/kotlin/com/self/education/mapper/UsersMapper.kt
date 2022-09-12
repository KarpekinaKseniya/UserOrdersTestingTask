package com.self.education.mapper

import com.self.education.api.UserResponse
import com.self.education.domain.User
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface UsersMapper {

    fun mapToResponse(user: User?): UserResponse
}