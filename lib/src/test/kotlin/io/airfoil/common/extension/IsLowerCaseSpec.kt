package io.airfoil.common.extension

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.*

private val LOWERCASE_STRINGS = listOf(
    "lowercase",
    "this is lowercase too",
    "l0w3rcas3 with numb3r5",
)

private val NON_LOWERCASE_STRINGS = listOf(
    "Uppercase",
    "UPPERCASE",
    "this is not Totally Lowercase",
    "lowercase with *()$%&@",
)

class IsLowerCaseSpec : FunSpec({

    context("isLowerCase") {
        test("SUCCESS - lowercase strings") {
            LOWERCASE_STRINGS.forEach { string ->
                string.isLowerCase() shouldBe true
            }
        }

        test("SUCCESS - non-lowercase strings") {
            NON_LOWERCASE_STRINGS.forEach { string ->
                string.isLowerCase() shouldBe false
            }
        }
    }

})
