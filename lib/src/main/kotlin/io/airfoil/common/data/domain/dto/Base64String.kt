package io.airfoil.common.data.domain.dto

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.Base64

@JvmInline
@kotlinx.serialization.Serializable(with = Base64StringSerializer::class)
value class Base64String(val value: String) {
    override fun toString() = value

    fun decode(): ByteArray =
        Base64.getDecoder().decode(value)

    companion object {
        fun encode(value: ByteArray): Base64String =
            Base64String(Base64.getEncoder().encodeToString(value))

        fun encode(value: String): Base64String =
            Base64String(Base64.getEncoder().encodeToString(value.toByteArray()))
    }
}

object Base64StringSerializer : KSerializer<Base64String> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        "io.airfoil.common.data.domain.dto.Base64StringSerializer",
        PrimitiveKind.STRING,
    )

    override fun deserialize(decoder: Decoder): Base64String {
        return Base64String(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: Base64String) {
        encoder.encodeString(value.value)
    }
}
