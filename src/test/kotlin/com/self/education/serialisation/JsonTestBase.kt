package com.self.education.serialisation

import com.fasterxml.jackson.core.JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN
import com.fasterxml.jackson.core.util.DefaultIndenter
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.function.Supplier


private const val FILE_PATH = "src/test/resources/serialisation/"

abstract class JsonTestBase<T> {

    protected lateinit var expected: Supplier<T>
    protected lateinit var fileName: String
    protected lateinit var expectedType: Class<T>

    private lateinit var mapper: ObjectMapper

    @BeforeEach
    fun setup() {
        mapper = ObjectMapper()
        mapper.registerModule(JavaTimeModule())
        mapper.enable(WRITE_BIGDECIMAL_AS_PLAIN)
    }

    @Test
    @Throws(IOException::class)
    fun shouldSerialize() {
        val dto: T = expected.get()
        val actualJson: String =
            mapper.writer(DefaultPrettyPrinter().withObjectIndenter(DefaultIndenter().withLinefeed("\n")))
                .writeValueAsString(dto)
        assertJson(actualJson, fileName)
    }

    @Test
    @Throws(IOException::class)
    fun shouldDeserialize() {
        val expected: T = expected.get()
        val expectedJson = readFileAsString(fileName)
        val actual: T = mapper.readValue(expectedJson, expectedType)
        assertThat(actual, `is`(expected))
    }

    @Throws(IOException::class)
    private fun assertJson(actualJson: String, expectedJsonFile: String?) {
        val expectedJson = readFileAsString(expectedJsonFile)
        assertThat("JSON strings did not match", actualJson, `is`(expectedJson))
    }

    @Throws(IOException::class)
    private fun readFileAsString(file: String?): String {
        return String(Files.readAllBytes(Paths.get(FILE_PATH + file)))
    }
}