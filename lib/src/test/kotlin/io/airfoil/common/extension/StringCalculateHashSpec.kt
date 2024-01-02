package io.airfoil.common.extension

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.*

private val TEST_STRING_8 = "Lorem ip"
private val TEST_STRING_32 = "Lorem ipsum dolor sit amet, con"
private val TEST_STRING_256 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in"

class StringCalculateHashSpec : FunSpec({

    context("calculateSHA256") {
        test("SUCCESS - returns valid SHA256 for 8 byte string") {
            TEST_STRING_8.calculateSHA256() shouldBe "58bb265574944b299bd1c16fbcdcd8994b67c0ee94db524916c0044b7ed24caa"
        }

        test("SUCCESS - returns valid SHA256 for 32 byte string") {
            TEST_STRING_32.calculateSHA256() shouldBe "11593ad5efab709497411c6d9ed887c9b3dcf5f245c4c2d5ba3ab3a453660b04"
        }

        test("SUCCESS - returns valid SHA256 for 256 byte string") {
            TEST_STRING_256.calculateSHA256() shouldBe "2fac5f5f1d048a84fbb75c389f4596e05023ac17da4fcf45a5954d2d9a394301"
        }
    }

    context("calculateMD5") {
        test("SUCCESS - returns valid MD5 for 8 byte string") {
            TEST_STRING_8.calculateMD5() shouldBe "889a44c4fe624ead16c9fc84f27a28d7"
        }

        test("SUCCESS - returns valid MD5 for 32 byte string") {
            TEST_STRING_32.calculateMD5() shouldBe "85af4b99e0109300562c5e40d6550f48"
        }

        test("SUCCESS - returns valid MD5 for 256 byte string") {
            TEST_STRING_256.calculateMD5() shouldBe "df803896b57da37f1fa2d885577e4007"
        }
    }

})
