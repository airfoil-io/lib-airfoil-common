package io.airfoil.common.extension

import io.airfoil.common.types.TestEnum
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

class EnumValueOrNullSpec : FunSpec({

    context("enumValueOrNull") {
        test("SUCCESS - returns enumeration for valid property") {
            TEST_GROUP_1.enumValueOrNull<TestEnum>("testEnumConfig") shouldBe TestEnum.ENUM_VALUE_1
        }

        test("FAILURE - returns null for invalid property") {
            TEST_GROUP_1.enumValueOrNull<TestEnum>("invalidEnumConfig") shouldBe null
        }
    }

})
