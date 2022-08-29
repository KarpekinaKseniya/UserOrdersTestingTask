package com.self.education

import com.self.education.config.LiquibaseConfiguration
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class Main

fun main(args: Array<String>) {
    AnnotationConfigApplicationContext(LiquibaseConfiguration::class.java)
}
