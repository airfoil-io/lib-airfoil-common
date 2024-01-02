package io.airfoil.common.data.domain.dto

import io.kotest.assertions.throwables.*
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.*
import io.kotest.matchers.types.*

class PersonNameSpec : FunSpec({

    context("initialization") {
        test("FAILURE - empty first name") {
            shouldThrow<IllegalArgumentException> {
                PersonName(
                    firstName = "",
                    lastName = "Herzinger",
                )
            }
        }

        test("FAILURE - empty last name") {
            shouldThrow<IllegalArgumentException> {
                PersonName(
                    firstName = "Kory",
                    lastName = "",
                )
            }
        }

        test("SUCCESS - missing middle name") {
            PersonName(
                firstName = "Kory",
                lastName = "Herzinger",
            ).shouldBeInstanceOf<PersonName>()
        }

        test("SUCCESS - including middle name") {
            PersonName(
                firstName = "Kory",
                middleName = "William",
                lastName = "Herzinger",
            ).shouldBeInstanceOf<PersonName>()
        }
    }

    context("displayName") {
        test("SUCCESS - missing middle name") {
            val name = PersonName(
                firstName = "Kory",
                lastName = "Herzinger",
            )
            name.displayName shouldBe "Kory Herzinger"
        }

        test("SUCCESS - including middle name") {
            val name = PersonName(
                firstName = "Kory",
                middleName = "William",
                lastName = "Herzinger",
            )
            name.displayName shouldBe "Kory William Herzinger"
        }
    }

})
