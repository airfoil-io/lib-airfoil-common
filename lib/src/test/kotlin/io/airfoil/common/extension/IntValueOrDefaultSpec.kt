package io.airfoil.common.extension

import io.kotest.assertions.throwables.*
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.*
import io.ktor.server.config.*
import io.ktor.server.config.yaml.YamlConfigLoader
import java.io.File

private val APPLICATION_CONFIG: ApplicationConfig = YamlConfigLoader().load(
    File(ClassLoader.getSystemClassLoader()
        .getResource("application-test.yaml")
        .toURI()
    ).toString()
)!!

private val TEST_GROUP_1: ApplicationConfig = APPLICATION_CONFIG.configOrNull("testGroup1")!!

class IntValueOrDefaultSpec : FunSpec({

    context("intValueOrDefault") {
        test("SUCCESS - returns integer for valid property") {
            TEST_GROUP_1.intValueOrDefault("testIntConfig", 9999) shouldBe 123
        }

        test("FAILURE - returns default value for invalid property") {
            TEST_GROUP_1.intValueOrDefault("invalidIntConfig", 9999) shouldBe 9999
        }
    }

})
