package io.airfoil.common.data.domain.dto

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.runCatching

@JvmInline
@Serializable(with = EmailAddressSerializer::class)
value class EmailAddress(val value: String) {
    
    init {
        require(value.isNotEmpty()) { "Email address cannot be empty" }
        require(EMAIL_ADDRESS_REGEX.toRegex().matches(value.lowercase())) { "Email address is invalid" }
    }

    override fun toString() = value.lowercase().toString()

    companion object {
        const val EMAIL_ADDRESS_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"

        operator fun invoke(string: String): EmailAddress? = runCatching {
            EmailAddress(string.lowercase())
        }.getOrNull()
    }
}

object EmailAddressSerializer : KSerializer<EmailAddress> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("io.airfoil.common.data.domain.dto.EmailAddressSerializer", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): EmailAddress =
        EmailAddress(decoder.decodeString())

    override fun serialize(encoder: Encoder, value: EmailAddress) {
        encoder.encodeString(value.toString())
    }
}
