import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.3"
    id("io.spring.dependency-management") version "1.0.12.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("kapt") version "1.7.10"
    id("jacoco")
}

group = "com.self.education"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

//DEPENDENCIES VERSION
val liquibaseVersion = "4.15.0"
val mongoJdbcVersion = "3.12.11"
val springVersion = "5.3.22"
val kotlinReflectVersion = "1.7.10"
val mapstructVersion = "1.5.2.Final"
val swaggerVersion = "1.6.11"
val mockitoVersion = "4.7.0"
val hamcrestVersion = "1.3"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.liquibase:liquibase-core:$liquibaseVersion")
    implementation("org.liquibase.ext:liquibase-mongodb:$liquibaseVersion")
    runtimeOnly("org.mongodb:mongo-java-driver:$mongoJdbcVersion")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinReflectVersion")
    implementation("org.mapstruct:mapstruct:$mapstructVersion")
    kapt("org.mapstruct:mapstruct-processor:$mapstructVersion")
    implementation("org.springdoc:springdoc-openapi-ui:$swaggerVersion")
    kapt("org.springframework.boot:spring-boot-configuration-processor")

    runtimeOnly(project(":frontend"))

    testImplementation(kotlin("test"))
    testImplementation("org.mockito:mockito-core:$mockitoVersion")
    testImplementation("org.hamcrest:hamcrest-all:$hamcrestVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework:spring-test:$springVersion")
    testImplementation("uk.co.datumedge:hamcrest-json:0.2")
}
kapt {
    correctErrorTypes = true
    keepJavacAnnotationProcessors = true
}

tasks.test {
    useJUnitPlatform()
    finalizedBy("jacocoTestReport")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

tasks.jacocoTestReport {
    classDirectories.setFrom(
        files(classDirectories.files.map {
            fileTree(it) {
                exclude("**/api/*", "**/domain/*", "**/config/*", "com/self/education/Main*.*")
            }
        })
    )
}