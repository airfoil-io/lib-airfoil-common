package io.airfoil.common.extension

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.*

private val UPPERCASE_STRINGS = listOf(
    "UPPERCASE",
    "THIS IS UPPERCASE TOO",
    "UPP3RCA5E WITH NUMV3R5",
)

private val NON_UPPERCASE_STRINGS = listOf(
    "Lowercase",
    "lowercase",
    "THIS IS NOT tOTALLY uPPERCASE",
    "UPPERCASE WITH *()$%&@",
)

class IsUpperCaseSpec : FunSpec({

    context("isUpperCase") {
        test("SUCCESS - uppercase strings") {
            UPPERCASE_STRINGS.forEach { string ->
                string.isUpperCase() shouldBe true
            }
        }

        test("SUCCESS - non-uppercase strings") {
            NON_UPPERCASE_STRINGS.forEach { string ->
                string.isUpperCase() shouldBe false
            }
        }
    }

})
