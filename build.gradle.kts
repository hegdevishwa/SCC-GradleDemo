/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    java
    `maven-publish`
    id("org.springframework.cloud.contract") version "3.1.3"
    id("org.springframework.boot") version "2.7.5"
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter:2.7.5")
    implementation("org.springframework.boot:spring-boot-starter-web:2.7.5")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.7.5")
    implementation("org.flywaydb:flyway-core:9.6.0")
    runtimeOnly("com.h2database:h2:2.1.214")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.7.5")
    testImplementation("org.springframework.cloud:spring-cloud-starter-contract-verifier:3.1.4")
    testImplementation("com.h2database:h2:2.1.214")
}

group = "com.contract.demo"
version = "0.0.1-SNAPSHOT"
description = "SpringContractDemo"
java.sourceCompatibility = JavaVersion.VERSION_1_8

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

contracts {
    basePackageForTests.set("com.contract.demo.springcontractdemo")
    testFramework.set(org.springframework.cloud.contract.verifier.config.TestFramework.JUNIT5)
    baseClassForTests.set("com.contract.demo.springcontractdemo.ContractBase")

}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}


var afterTest = KotlinClosure2({ desc: TestDescriptor, result: TestResult ->
    if (desc.parent == null) {
        if (result.testCount == 0L) {
            throw IllegalStateException("No tests were found. Failing the build")
        }
        else {
            println("Results: (${result.testCount} tests, ${result.successfulTestCount} successes, ${result.failedTestCount} failures, ${result.skippedTestCount} skipped)")
        }
    } else { /* Nothing to do here */ }
})

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging{
        showStandardStreams = true
    }
    afterSuite(afterTest)
}

tasks {
    contractTest {
        useJUnitPlatform()
        //systemProperty("spring.profiles.active", "test")
        testLogging {
            showStandardStreams = true
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        }
        afterSuite(afterTest)
    }
}