package io.airfoil.common.data.domain.dto

import io.kotest.assertions.throwables.*
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.*

private val VALID_PHONE_NUMBERS = listOf(
    "00:00:00:00:00:00",
    "01:23:45:67:89:AB",
    "aa:bb:cc:DD:EE:FF",
    "000000000000",
    "0123456789AB",
    "aabbccDDEEFF",
)

private val INVALID_MAC_ADDRESSES = listOf(
    "invalid-mac-address",
    "00:01:02:03:04:GG",
    "abcdefghijhi",
)

class MACAddressSpec : FunSpec({

    context("initialization") {
        test("FAILURE - empty MAC address") {
            shouldThrow<IllegalArgumentException> {
                MACAddress("")
            }
        }

        test("FAILURE - invalid MAC address length") {
            shouldThrow<IllegalArgumentException> {
                MACAddress("00")
            }
        }

        test("SUCCESS - valid MAC addresses") {
            VALID_PHONE_NUMBERS.forEach { validMACAddress ->
                val macAddress = MACAddress(validMACAddress)
                macAddress.value shouldBe validMACAddress
            }
        }

        test("FAILURE - invalid MAC addresses") {
            INVALID_MAC_ADDRESSES.forEach { invalidMACAddress ->
                shouldThrow<IllegalArgumentException> {
                    MACAddress(invalidMACAddress)
                }
            }
        }
    }

})
