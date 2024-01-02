package io.airfoil.common.data.domain.dto

import io.kotest.assertions.throwables.*
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.*

private val VALID_PHONE_NUMBERS = listOf(
    "5551234567",
    "555-123-4567",
    "555 123 4567",
    "555.123.4567",
    "(555) 123-4567",
    "+15551234567",
    "+911234567890",
    "+44 2034567890x456",
)

private val INVALID_PHONE_NUMBERS = listOf(
    "invalid-phone",
    "+15551234567890123",
    "+996+5-95dsf"
)

class PhoneNumberSpec : FunSpec({

    context("initialization") {
        test("FAILURE - empty phone number") {
            shouldThrow<IllegalArgumentException> {
                PhoneNumber("")
            }
        }

        test("SUCCESS - valid phone numbers") {
            VALID_PHONE_NUMBERS.forEach { validPhoneNumber ->
                val phoneNumber = PhoneNumber(validPhoneNumber)
                phoneNumber.value shouldBe validPhoneNumber
            }
        }

        test("FAILURE - invalid phone numbers") {
            INVALID_PHONE_NUMBERS.forEach { invalidPhoneNumber ->
                shouldThrow<IllegalArgumentException> {
                    PhoneNumber(invalidPhoneNumber)
                }
            }
        }
    }
})
