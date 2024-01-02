package io.airfoil.common.data.domain.dto

import io.kotest.assertions.throwables.*
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.*
import io.kotest.matchers.types.*

private val VALID_KEYS = listOf(
    "Jlfix6i1rYrjZsEP",
    "IjTCrkVcNlw99RQy",
    "fttSnMR15mYNfyN8",
    "hV5Fug2D7vz1UqWV",
    "AUtJ518HvhPKvW2N",
)

private val INVALID_KEYS = listOf(
    "QN3RaZX",
    "fvtE%+;G)376Uz(:",
    "DWZN@QH{Y(Xj4ng_",
    "Q`Jsq]d39mRnFWgD",
    "NLW8+rgPS_^sb,vf",
)

class Key16Spec : FunSpec({

    context("initialization") {
        test("FAILURE - empty key") {
            shouldThrow<IllegalArgumentException> {
                Key16("")
            }
        }

        test("FAILURE - invalid key length") {
            shouldThrow<IllegalArgumentException> {
                Key16("badlength")
            }
        }

        test("SUCCESS - valid keys") {
            VALID_KEYS.forEach { validKey ->
                val key = Key16(validKey)
                key.value shouldBe validKey
            }
        }

        test("FAILURE - invalid keys") {
            INVALID_KEYS.forEach { invalidKey ->
                shouldThrow<IllegalArgumentException> {
                    Key16(invalidKey)
                }
            }
        }
    }

    context("random") {
        test("SUCCESS - generates valid random key") {
            Key16.random().shouldBeInstanceOf<Key16>()
        }
    }

})
