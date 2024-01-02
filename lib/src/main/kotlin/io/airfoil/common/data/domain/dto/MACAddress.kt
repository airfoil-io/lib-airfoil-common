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
@Serializable(with = MACAddressSerializer::class)
value class MACAddress(val value: String) {

    init {
        require(value.isNotEmpty()) { "MAC address cannot be empty" }
        require(value.length == MAC_ADDRESS_LENGTH || value.length == MAC_ADDRESS_WITH_SEP_LENGTH) { "Invalid MAC address length: ${value.length}" }
        require(MAC_ADDRESS_REGEX.toRegex().matches(value) || MAC_ADDRESS_WITH_SEP_REGEX.toRegex().matches(value)) { "MAC address is invalid" }
    }

    override fun toString(): String = 
        if (MAC_ADDRESS_WITH_SEP_REGEX.toRegex().matches(value)) {
            value
        } else {
            value.split("").chunked(2).map { "${it[0]}${it[1]}" }.joinToString(":")
        }

    fun toStringNoSep(): String =
        if (MAC_ADDRESS_REGEX.toRegex().matches(value)) {
            value
        } else {
            value.split(":").joinToString("")
        }

    companion object {
        const val MAC_ADDRESS_LENGTH = 12
        const val MAC_ADDRESS_REGEX = "^[a-fA-F0-9]{$MAC_ADDRESS_LENGTH}$"
        const val MAC_ADDRESS_WITH_SEP_LENGTH = 17
        const val MAC_ADDRESS_WITH_SEP_REGEX = "^[a-fA-F0-9]{2}:[a-fA-F0-9]{2}:[a-fA-F0-9]{2}:[a-fA-F0-9]{2}:[a-fA-F0-9]{2}:[a-fA-F0-9]{2}$"

        operator fun invoke(string: String): MACAddress? = runCatching {
            MACAddress(string)
        }.getOrNull()
    }
}

object MACAddressSerializer : KSerializer<MACAddress> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("io.airfoil.common.data.domain.dto.MACAddressSerializer", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): MACAddress =
        MACAddress(decoder.decodeString())

    override fun serialize(encoder: Encoder, value: MACAddress) {
        encoder.encodeString(value.toString())
    }
}
