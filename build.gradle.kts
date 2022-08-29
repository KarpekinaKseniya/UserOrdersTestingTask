import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
}

group = "com.self.education"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

//#DEPENDENCIES VERSION
val liquibaseVersion = "4.15.0"
val mongoJdbcVersion = "3.12.11"
val springVersion = "5.3.22"

dependencies {
    implementation("org.springframework:spring-context:$springVersion")
    implementation("org.liquibase:liquibase-core:$liquibaseVersion")
    implementation("org.liquibase.ext:liquibase-mongodb:$liquibaseVersion")
    implementation("org.mongodb:mongo-java-driver:$mongoJdbcVersion")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}