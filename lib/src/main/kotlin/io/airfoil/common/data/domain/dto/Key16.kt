package io.airfoil.common.data.domain.dto

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.apache.commons.text.RandomStringGenerator
import java.security.SecureRandom
import kotlin.runCatching

@JvmInline
@Serializable(with = Key16Serializer::class)
value class Key16(val value: String) {
    
    init {
        require(value.isNotEmpty()) { "Key cannot be empty" }
        require(value.length == KEY_LENGTH) { "Invalid key length: ${value.length}, expected ${KEY_LENGTH}" }
        require(KEY_REGEX.toRegex().matches(value)) { "Key is invalid" }
    }

    override fun toString() = value

    companion object {
        const val KEY_LENGTH = 16
        const val KEY_REGEX = "^[a-zA-Z0-9]{$KEY_LENGTH}$"

        operator fun invoke(string: String): Key16? = runCatching {
            Key16(string)
        }.getOrNull()

        fun random() = SecureRandom().let { rng ->
            RandomStringGenerator.Builder()
                .selectFrom(*("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray()))
                .usingRandom(rng::nextInt)
                .build()
                .let { Key16(it.generate(KEY_LENGTH)) }
        }
    }
}

object Key16Serializer : KSerializer<Key16> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("io.airfoil.common.data.domain.dto.Key16Serializer", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Key16 =
        Key16(decoder.decodeString())

    override fun serialize(encoder: Encoder, value: Key16) {
        encoder.encodeString(value.toString())
    }
}
