package io.airfoil.common.data.domain.dto

import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class PhoneNumber(val value: String) {

    init {
        val phoneNumberUtil = PhoneNumberUtil.getInstance()

        require(value.isNotEmpty()) { "Phone number cannot be empty" }
        try {
            require(phoneNumberUtil.isPossibleNumber(phoneNumberUtil.parse(value, "US")))
                { "Phone number is invalid" }
        } catch (ex: NumberParseException) {
            throw IllegalArgumentException("Phone number is invalid")
        }
    }
}
