package io.airfoil.common.data.domain.dto

import io.kotest.assertions.throwables.*
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.*
import kotlinx.serialization.json.Json

private val VALID_PASSWORDS = listOf(
    "54sEWzyV",
    "sTq43DNLG8",
    "HZkGhLs7XmhBvezN",
    "3?Zb_B2Eb;7qYP\$n",
    "C?V:k~GZ95AeAK\$eLD.msj&PtQR=_r9@",
)

private val INVALID_PASSWORDS = listOf(
    "1234",
    "aaaa",
    "12345678",
    "aaaaaaaa",
    "abcd1234",
    "ABCD1234",
    "n3xpB6"
)

class PasswordSpec : FunSpec({

    context("initialization") {
        test("FAILURE - empty password") {
            shouldThrow<IllegalArgumentException> {
                Password("")
            }
        }

        test("SUCCESS - valid passwords") {
            VALID_PASSWORDS.forEach { validPassword ->
                val password = Password(validPassword)
                password.value shouldBe validPassword
            }
        }

        test("FAILURE - invalid passwords") {
            INVALID_PASSWORDS.forEach { invalidPassword ->
                shouldThrow<IllegalArgumentException> {
                    Password(invalidPassword)
                }
            }
        }
    }

    context("toString") {
        test("SUCCESS - hide password") {
            val password = Password(VALID_PASSWORDS[0])

            password.value shouldBe VALID_PASSWORDS[0]
            password.toString() shouldBe ""
        }
    }

    context("serialization") {
        test("SUCCESS - don't serialize password") {
            val password = Password(VALID_PASSWORDS[0])
            val serializedPassword = Json.encodeToString(PasswordSerializer, password)

            serializedPassword shouldBe "\"\""
        }
    }

})
