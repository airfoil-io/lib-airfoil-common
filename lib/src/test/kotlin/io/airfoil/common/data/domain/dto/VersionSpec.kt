package io.airfoil.common.data.domain.dto

import io.kotest.assertions.throwables.*
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.*

private val VALID_VERSIONS = listOf(
    "0.0.0",
    "1.2.3",
    "999.999.999",
)

private val INVALID_VERSIONS = listOf(
    "invalid-version",
    "-1.-1.-1",
    "1000.0.0",
    "0.1000.0",
    "0.0.1000",
)

class VersionSpec : FunSpec({

    context("initialization") {
        test("SUCCESS - valid versions") {
            VALID_VERSIONS.forEach { validVersion ->
                val version = Version(validVersion)
                version.toString() shouldBe validVersion
            }
        }

        test("FAILURE - invalid versions") {
            INVALID_VERSIONS.forEach { invalidVersion ->
                shouldThrow<IllegalArgumentException> {
                    Version(invalidVersion)
                }
            }
        }
    }

    context("toInt") {
        test("SUCCESS - valid integer value") {
            val version = Version("1.23.45")
            version.toInt() shouldBe 1023045
        }
    }

})
