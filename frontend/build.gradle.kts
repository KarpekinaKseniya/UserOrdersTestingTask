import com.github.gradle.node.npm.task.NpmTask

buildscript {
    repositories {
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath("com.github.node-gradle:gradle-node-plugin:3.4.0")
    }
}

plugins {
    base
}

apply(plugin = "com.github.node-gradle.node")

tasks.named<NpmTask>("npm_run_build") {
    inputs.files(fileTree("public"))
    inputs.files(fileTree("src"))

    inputs.file("package.json")
    inputs.file("package-lock.json")

    outputs.dir("build")
}

val packageNpmApp by tasks.registering(Jar::class) {
    dependsOn("npm_run_build")
    baseName = "npm-app"
    extension = "jar"
    destinationDir = file("${projectDir}/build_npm")
    from("build") {
        into("static")
    }
}

val npmResources by configurations.creating

configurations.named("default").get().extendsFrom(npmResources)

artifacts {
    add(npmResources.name, packageNpmApp.get().archiveFile) {
        builtBy(packageNpmApp)
        type = "jar"
    }
}

tasks.assemble {
    dependsOn(packageNpmApp)
}

tasks.clean {
    delete(packageNpmApp.get().archiveFile)
}