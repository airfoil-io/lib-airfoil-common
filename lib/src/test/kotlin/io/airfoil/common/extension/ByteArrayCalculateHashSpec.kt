package io.airfoil.common.extension

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.*
import java.io.File
import java.nio.file.Files

private val TEST_DATA_8K: ByteArray = Files.readAllBytes(
    File(ClassLoader.getSystemClassLoader()
        .getResource("test-8k-data.bin")
        .toURI()
    ).toPath()
)

private val TEST_DATA_32K: ByteArray = Files.readAllBytes(
    File(ClassLoader.getSystemClassLoader()
        .getResource("test-32k-data.bin")
        .toURI()
    ).toPath()
)

private val TEST_DATA_256K: ByteArray = Files.readAllBytes(
    File(ClassLoader.getSystemClassLoader()
        .getResource("test-256k-data.bin")
        .toURI()
    ).toPath()
)

class ByteArrayCalculateHashSpec : FunSpec({

    context("calculateSHA256") {
        test("SUCCESS - returns valid SHA256 for 8k binary data") {
            TEST_DATA_8K.calculateSHA256() shouldBe "37eb68daa5bb54e06525d270a710ebcf2c9db6042f2793531a368c977e1b04b7"
        }

        test("SUCCESS - returns valid SHA256 for 32k binary data") {
            TEST_DATA_32K.calculateSHA256() shouldBe "1e2a85003cc802a7228e8d16191e118563cdcb1790e5f885c9685f31a865afe1"
        }

        test("SUCCESS - returns valid SHA256 for 256k binary data") {
            TEST_DATA_256K.calculateSHA256() shouldBe "7ad2a97faa9a1ba4ac40bd130ee58e45a6b973bfa1fdbd9d6a2e68c9e219c077"
        }
    }

    context("calculateMD5") {
        test("SUCCESS - returns valid MD5 for 8k binary data") {
            TEST_DATA_8K.calculateMD5() shouldBe "1f3f1fec57ca4f099b2eb43f3af98918"
        }

        test("SUCCESS - returns valid MD5 for 32k binary data") {
            TEST_DATA_32K.calculateMD5() shouldBe "7126e9819709cb5f1aa7c65b46d12102"
        }

        test("SUCCESS - returns valid MD5 for 256k binary data") {
            TEST_DATA_256K.calculateMD5() shouldBe "7b8997c6ec6b6e62d352aeeb6ccc8c17"
        }
    }

})
