/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin library project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/7.4.2/userguide/building_java_projects.html
 * This project uses @Incubating APIs which are subject to change.
 */

import io.qameta.allure.gradle.base.AllureExtension
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // Apply the java plugin to add support for Java
    java

    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.5.31" // 1.6.21

    // Apply the java-library plugin for API and implementation separation.
    `java-library`

    "application"

    id("io.freefair.lombok") version ("6.5.0.2")
    // id("org.jetbrains.kotlin.kapt") version ("1.5.31")
    // kotlin("plugin.lombok") version ("1.5.31")

    id("io.qameta.allure") version "2.9.4"

    // jacoco
    // pmd
    // id("org.jlleitschuh.gradle.ktlint") version ("10.3.0")
    // id("com.diffplug.spotless") version "6.8.0" // "6.6.1" // "6.8.0"
    // id("com.palantir.baseline") version "4.139.0"
    // id("com.github.spotbugs") version "5.0.7"
    // id("org.sonarqube") version "3.3"
    // id("io.gitlab.arturbosch.detekt").version("1.21.0-RC2")
}



java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(Version.JAVA.id.toInt()))
    }
}
tasks.compileJava {
    options.release.set(Version.JAVA.id.toInt())
    options.encoding = "UTF-8"
}
tasks.compileTestJava {
    options.release.set(Version.JAVA.id.toInt())
    options.encoding = "UTF-8"
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.suppressWarnings = true
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
        suppressWarnings = true
        javaParameters = true
    }
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions.suppressWarnings = true
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
        suppressWarnings = true
        javaParameters = true
    }
}

sourceSets {
    main {
        java.srcDirs(
            listOf(
                SourceSet.MAIN_JAVA.path,
                SourceSet.MAIN_KOTLIN.path
            )
        )
    }
    test {
        java.srcDirs(
            listOf(
                SourceSet.TEST_JAVA.path,
                SourceSet.TEST_KOTLIN.path
            )
        )
    }
}



repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Version.KOTLIN.id}")

    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // This dependency is used internally, and not exposed to consumers on their own compile classpath.
    implementation("com.google.guava:guava:30.1.1-jre")

    // This dependency is exported to consumers, that is to say found on their compile classpath.
    api("org.apache.commons:commons-math3:3.6.1")

    // TODO: temporarily added
    // Use the Kotlin test library.
    // testImplementation("org.jetbrains.kotlin:kotlin-test:${Version.KOTLIN.id}")

    // Use the Kotlin JUnit integration.
    // testImplementation("org.jetbrains.kotlin:kotlin-test-junit:${Version.KOTLIN.id}")


    // testImplementation("io.qameta.allure:allure-java-commons:${Version.ALLURE.id}")
    implementation("io.qameta.allure:allure-java-commons:${Version.ALLURE.id}")
    implementation("io.qameta.allure:allure-cucumber6-jvm:${Version.ALLURE.id}")
    // testImplementation("io.qameta.allure:allure-junit4:${Version.ALLURE.id}")

    implementation("io.qameta.allure:allure-attachments:${Version.ALLURE.id}")

    // implementation("io.qameta.allure:allure-rest-assured:${Version.ALLURE.id}")
    // implementation("io.qameta.allure:allure-generator:${Version.ALLURE.id}")
    testRuntimeOnly("io.qameta.allure:allure-junit5:${Version.ALLURE.id}")


    runtimeOnly("org.aspectj:aspectjweaver:${Version.ASPECTJ.id}")

    testCompileOnly("org.projectlombok:lombok:${Version.LOMBOK.id}")
    testAnnotationProcessor("org.projectlombok:lombok:${Version.LOMBOK.id}")

    implementation("io.github.cdimascio:java-dotenv:5.2.2")

    implementation("net.datafaker:datafaker:1.4.0")

    implementation("org.slf4j:slf4j-api:1.7.36")
    testImplementation("ch.qos.logback:logback-classic:${Version.LOGBACK.id}")
    implementation("ch.qos.logback:logback-core:${Version.LOGBACK.id}")

    // implementation("com.google.dagger:dagger-compiler:${Version.DAGGER.id}")
    // kapt("com.google.dagger:dagger-compiler:${Version.DAGGER.id}")

    implementation("io.appium:java-client:8.1.1")
    testImplementation("org.testng:testng:7.6.1")
}

tasks.register<JavaExec>("playwrightInstall")
tasks.withType<JavaExec>().configureEach {
    if (name.matches("playwrightInstall".toRegex())) {
        group = "Execution"
        description = "Run installation of Playwright browsers"
        classpath = sourceSets.main.get().runtimeClasspath
        mainClass.set("com.microsoft.playwright.CLI")
        args = listOf("install")
    }
}

tasks.register<JavaExec>("runPlaywrightCodegen")
tasks.withType<JavaExec>().configureEach {
    if (name.matches("runPlaywrightCodegen".toRegex())) {
        group = "Execution"
        description = "Run Playwright codegen"
        classpath = sourceSets.main.get().runtimeClasspath
        mainClass.set("com.microsoft.playwright.CLI")
        args = listOf("codegen")
    }
}

tasks.register<JavaExec>("sonarCheck")
tasks.withType<JavaExec>().configureEach {
    if (name.matches("sonarCheck".toRegex())) {
        group = "Execution"
        description = "Run Sonar check"
        classpath = sourceSets.main.get().runtimeClasspath
        // mainClass.set("com.microsoft.playwright.CLI")
        args = listOf("sonar.analysis.mode=preview", "sonar.issuesReport.html.enable=true")
    }
}

testing {
    suites {
        // Configure the built-in test suite
        val test by getting(JvmTestSuite::class) {
            // Use Kotlin Test test framework
            useKotlinTest()
        }
    }
}

configure<AllureExtension> {
    // autoconfigure = true
    // aspectjweaver = true
    adapter {
        // version = Version.ALLURE.id
        // allureJavaVersion = Version.JAVA.id
        // configuration = "testImplementation"

        // clean = true

        // resultsDir = file("../../target/allure-results")
        // reportDir = file("../../target/allure-reports")
    }

    useJUnit5 {
        version = Version.ALLURE.id
    }
}

tasks.test {
    filter {
        // exclude("/e2e/**")
        // exclude("TestRunner")
        // exclude("/tech/inno/e2e/testing/ui/**")
        // exclude("/tech/inno/e2e/testing/rest/**")
        exclude("/tech/inno/e2e/testing/unit/**")
    }
    // useJUnitPlatform()
    useTestNG()
    // TODO: remove this one if not needed
    testLogging.showStandardStreams = true
    testLogging.displayGranularity = 2
    testLogging.showCauses = true
    testLogging.showExceptions = true
    // testLogging.events = setOf(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED, TestLogEvent.STANDARD_ERROR, TestLogEvent.STANDARD_OUT, TestLogEvent.STARTED)
    testLogging.events = setOf(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)
    testLogging.showStackTraces
    testLogging.exceptionFormat = TestExceptionFormat.FULL
    maxParallelForks = 3
    systemProperty("junit.jupiter.execution.parallel.enabled", "true")
    systemProperty("junit.jupiter.execution.parallel.config.strategy", "dynamic")
    systemProperty("junit.jupiter.extensions.autodetection.enabled", "true")
    ignoreFailures = true
}



enum class Version(val id: String) {
    JUNIT_JUPITER("5.8.2"),
    JUNIT_PLATFORM("1.8.2"),
    JACKSON("2.13.3"),
    SNAKEYAML("1.30"),
    KAFKA("3.2.0"),
    JOOQ("3.16.6"),
    CLICKHOUSE("0.3.2"),
    CLICKHOUSE_CLIENT("0.3.2-patch10"),
    POSTGRESQL("42.4.0"),
    TIKA("2.4.1"),
    REST_ASSURED("5.1.1"),
    HAMCREST("2.2"),
    AWAITILITY("4.2.0"),
    RETROFIT("2.9.0"),
    OKHTTP3("4.9.3"),
    PLAYWRIGHT("1.23.0"),
    ALLURE("2.18.1"),
    LOGBACK("1.2.11"),
    DAGGER("2.42"),
    JAVA("11"),
    KOTLIN("1.5.31"),
    LOMBOK("1.18.24"),
    ASPECTJ("1.9.9.1"),
    BITCOINJ("0.16.1"),
    WEB3J("5.0.0"),
    GRADLE("7.4.1"),
    PMD("6.47.0"),
    KTLINT("0.46.1");
}

enum class SourceSet(val path: String) {
    MAIN_JAVA("src/main/java"),
    MAIN_KOTLIN("src/main/kotlin"),
    TEST_JAVA("src/test/java"),
    TEST_KOTLIN("src/test/kotlin");
}
