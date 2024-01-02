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

class DoubleValueOrErrorSpec : FunSpec({

    context("doubleValueOrError") {
        test("SUCCESS - returns double for valid property") {
            TEST_GROUP_1.doubleValueOrError("testDoubleConfig", "Property not found") shouldBe 123.456
        }

        test("FAILURE - throws exception for invalid property") {
            shouldThrow<ApplicationConfigurationException> {
                TEST_GROUP_1.doubleValueOrError("invalidDoubleConfig", "Property not found")
            }
        }
    }

})
