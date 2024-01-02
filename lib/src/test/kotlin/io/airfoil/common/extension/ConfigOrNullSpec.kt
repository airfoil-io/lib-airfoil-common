package io.airfoil.common.extension

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.*
import io.kotest.matchers.types.*
import io.ktor.server.config.*
import io.ktor.server.config.yaml.YamlConfigLoader
import java.io.File

private val APPLICATION_CONFIG: ApplicationConfig = YamlConfigLoader().load(
    File(ClassLoader.getSystemClassLoader()
        .getResource("application-test.yaml")
        .toURI()
    ).toString()
)!!

class ConfigOrNullSpec : FunSpec({

    context("configOrNull") {
        test("SUCCESS - returns valid config for valid path") {
            APPLICATION_CONFIG.configOrNull("testGroup1").shouldBeInstanceOf<ApplicationConfig>()
        }

        test("FAILURE - returns null for invalid path") {
            APPLICATION_CONFIG.configOrNull("invalidPath") shouldBe null
        }
    }

})
