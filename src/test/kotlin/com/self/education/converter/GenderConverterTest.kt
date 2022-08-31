package com.self.education.converter

import com.self.education.domain.Gender
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.springframework.core.convert.converter.Converter

internal class GenderConverterTest {

    private val converter: Converter<String, Gender> = GenderConverter()

    @TestFactory
    fun shouldConvert() = listOf(
        "Male" to Gender.MALE,
        "female" to Gender.FEMALE,
        "MaLE" to Gender.MALE,
        "maLe" to Gender.MALE,
        "FEmAlE" to Gender.FEMALE,
        "" to null,
        "wrong" to null
    )
        .map { (input, expected) ->
            DynamicTest.dynamicTest("when I input $input^2 then I get $expected") {
                Assertions.assertEquals(expected, converter.convert(input))
            }
        }
}