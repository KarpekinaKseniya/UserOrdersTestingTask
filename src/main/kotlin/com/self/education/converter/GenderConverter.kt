package com.self.education.converter

import com.self.education.domain.Gender
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter


@ReadingConverter
class GenderConverter : Converter<String, Gender> {

    override fun convert(source: String): Gender? {
        return Gender.values().firstOrNull { gender: Gender -> gender.name == source.uppercase() }
    }
}