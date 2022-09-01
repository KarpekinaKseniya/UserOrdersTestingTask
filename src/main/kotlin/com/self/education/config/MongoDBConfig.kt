package com.self.education.config

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.self.education.converter.GenderConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories(basePackages = ["com.self.education.repository"])
open class MongoDBConfig(
    @Value("\${spring.data.mongodb.database}")
    private val databaseName: String,
    @Value("\${spring.data.mongodb.uri}")
    private val connectionUri: String
) : AbstractMongoClientConfiguration() {

    override fun getDatabaseName(): String {
        return databaseName
    }

    override fun customConversions(): MongoCustomConversions {
        return MongoCustomConversions(listOf(GenderConverter()))
    }

    override fun mongoClient(): MongoClient {
        //@formatter:off
        val mongoClientSettings: MongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(ConnectionString(connectionUri))
            .build()
        //@formatter:on
        return MongoClients.create(mongoClientSettings)
    }
}