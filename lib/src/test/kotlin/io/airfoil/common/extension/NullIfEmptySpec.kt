package io.airfoil.common.extension

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.*

class NullIfEmptySpec : FunSpec({

    context("nullIfEmpty") {
        test("SUCCESS - empty string returns null") {
            "".nullIfEmpty() shouldBe null
        }

        test("SUCCESS - non-empty string does not return null") {
            val nonEmptyString = "non-empty string"
            nonEmptyString.nullIfEmpty() shouldBe nonEmptyString
        }
    }

})
