package io.airfoil.common.extension

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

class StringListOrNullSpec : FunSpec({

    context("stringListOrNull") {
        test("SUCCESS - returns string list for valid property") {
            TEST_GROUP_1.stringListOrNull("testStringListConfig") shouldBe listOf(
                "string1",
                "string2",
                "string3",
            )
        }

        test("FAILURE - returns null for invalid property") {
            TEST_GROUP_1.stringListOrNull("invalidStringListConfig") shouldBe null
        }
    }

})
