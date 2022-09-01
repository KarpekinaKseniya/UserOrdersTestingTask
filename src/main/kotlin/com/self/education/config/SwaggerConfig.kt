package com.self.education.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
open class SwaggerConfig {

    @Bean
    open fun customOpenAPI(info: Info?): OpenAPI {
        return OpenAPI().info(info)
    }

    @Bean
    open fun info(): Info {
        //@formatter:off
        return Info()
            .title("Frontend Testing Task")
            .version("v1")
            .description("Contain solution on testing task")
        //@formatter:on
    }
}