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

class BoolValueOrErrorSpec : FunSpec({

    context("boolValueOrError") {
        test("SUCCESS - returns boolean for valid property") {
            TEST_GROUP_1.boolValueOrError("testBoolConfig", "Property not found") shouldBe true
        }

        test("FAILURE - throws exception for invalid property") {
            shouldThrow<ApplicationConfigurationException> {
                TEST_GROUP_1.boolValueOrError("invalidBoolConfig", "Property not found")
            }
        }
    }

})
