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

class StringValueOrDefaultSpec : FunSpec({

    context("stringValueOrDefault") {
        test("SUCCESS - returns string for valid property") {
            TEST_GROUP_1.stringValueOrDefault("testStringConfig", "default") shouldBe "string"
        }

        test("FAILURE - returns default value for invalid property") {
            TEST_GROUP_1.stringValueOrDefault("invalidStringConfig", "default") shouldBe "default"
        }
    }

})
