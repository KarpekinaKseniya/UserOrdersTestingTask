package com.self.education.config

import liquibase.Contexts
import liquibase.Liquibase
import liquibase.database.DatabaseFactory
import liquibase.resource.ClassLoaderResourceAccessor
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.io.Resource
import java.io.File

@Configuration
@PropertySource("classpath:application.properties")
open class LiquibaseConfiguration(
    @Value("\${spring.liquibase.url}")
    private val uri: String,
    @Value("\${spring.liquibase.change-log}")
    private val resources: Array<Resource>
) : InitializingBean {

    @Throws(Exception::class)
    override fun afterPropertiesSet() {
        val openDatabase = DatabaseFactory.getInstance().openDatabase(uri, null, null, null, null, null)
        for (resource in resources) {
            val filename: String = resource.getFilename()
            val parentFolder: String = resource.getFile().getParentFile().getName()
            Liquibase(
                java.lang.String.format("%s%s%s", parentFolder, File.separator, filename),
                ClassLoaderResourceAccessor(),
                openDatabase
            ).use { liquibase -> liquibase.update(Contexts()) }
        }
    }
}