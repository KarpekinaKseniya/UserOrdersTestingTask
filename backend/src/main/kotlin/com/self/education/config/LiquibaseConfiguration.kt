package com.self.education.config

import liquibase.Contexts
import liquibase.Liquibase
import liquibase.database.DatabaseFactory
import liquibase.resource.ClassLoaderResourceAccessor
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource


@Configuration
@PropertySource("classpath:application.properties")
open class LiquibaseConfiguration(
    @Value("\${spring.liquibase.url}")
    private val uri: String,
    @Value("\${spring.liquibase.change-log}")
    private val resources: String,
    @Value("\${spring.liquibase.enabled}")
    private val enabled: Boolean
) : InitializingBean {

    @Throws(Exception::class)
    override fun afterPropertiesSet() {
        if (enabled) {
            val openDatabase = DatabaseFactory.getInstance().openDatabase(uri, null, null, null, null, null)
            val liquibase = Liquibase(resources, ClassLoaderResourceAccessor(), openDatabase)
            liquibase.update(Contexts())
        }
    }
}