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

class DoubleValueOrDefaultSpec : FunSpec({

    context("doubleValueOrDefault") {
        test("SUCCESS - returns double for valid property") {
            TEST_GROUP_1.doubleValueOrDefault("testDoubleConfig", 999.999) shouldBe 123.456
        }

        test("FAILURE - returns default value for invalid property") {
            TEST_GROUP_1.doubleValueOrDefault("invalidDoubleConfig", 999.999) shouldBe 999.999
        }
    }

})
