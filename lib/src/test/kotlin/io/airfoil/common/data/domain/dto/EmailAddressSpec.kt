package io.airfoil.common.data.domain.dto

import io.kotest.assertions.throwables.*
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.*

private val VALID_EMAIL_ADDRESSES = listOf(
    "email@example.com",
    "firstname.lastname@example.com",
    "email@subdomain.example.com",
    "firstname+lastname@example.com",
    "email@123.123.123.123",
    "email@[123.123.123.123]",
    "\"email\"@example.com",
    "1234567890@example.com",
    "email@example-one.com",
    "_______@example.com",
    "email@example.name",
    "email@example.museum",
    "email@example.co.jp",
    "firstname-lastname@example.com",
)

private val INVALID_EMAIL_ADDRESSES = listOf(
    "plainaddress",
    "#@%^%#$@#$@#.com",
    "@example.com",
    "Joe Smith <email@example.com>",
    "email.example.com",
    "email@example@example.com",
    ".email@example.com",
    "email.@example.com",
    "email..email@example.com",
    "あいうえお@example.com",
    "email@example.com (Joe Smith)",
    "email@example",
    "email@-example.com",
    "email@example..com",
    "Abc..123@example.com",
    "\"(),:;<>[\\]@example.com",
    "just\"not\"right@example.com",
    "this\\ is\"really\"not\\allowed@example.com",
)

class EmailAddressSpec : FunSpec({

    context("initialization") {
        test("FAILURE - empty email address") {
            shouldThrow<IllegalArgumentException> {
                EmailAddress("")
            }
        }

        test("SUCCESS - from valid string") {
            EmailAddress.invoke(VALID_EMAIL_ADDRESSES[0]) shouldBe EmailAddress(VALID_EMAIL_ADDRESSES[0])
        }

        test("FAILURE - from invalid string") {
            EmailAddress.invoke(INVALID_EMAIL_ADDRESSES[0]) shouldBe null
        }

        test("SUCCESS - valid email addresses") {
            VALID_EMAIL_ADDRESSES.forEach { validEmailAddress ->
                val emailAddress = EmailAddress(validEmailAddress)
                emailAddress.value shouldBe validEmailAddress
            }
        }

        test("FAILURE - invalid email addresses") {
            INVALID_EMAIL_ADDRESSES.forEach { invalidEmailAddress ->
                shouldThrow<IllegalArgumentException> {
                    EmailAddress(invalidEmailAddress)
                }
            }
        }
    }

    context("toString") {
        test("SUCCESS - lowercase email address") {
            val emailAddress = EmailAddress("Busy.Pastor@CHURCH.com")

            emailAddress.value shouldBe "Busy.Pastor@CHURCH.com"
            emailAddress.toString() shouldBe "busy.pastor@church.com"
        }
    }

})
