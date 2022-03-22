pluginManagement {

    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

    plugins {
        id("org.springframework.boot") version ("2.6.4")
        id("io.spring.dependency-management") version ("1.0.11.RELEASE")
        id("com.github.node-gradle.node") version ("3.1.1")
    }
}

rootProject.name = "spring-react-starter"
