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
@Serializable(with = Key32Serializer::class)
value class Key32(val value: String) {
    
    init {
        require(value.isNotEmpty()) { "Key cannot be empty" }
        require(value.length == KEY_LENGTH) { "Invalid key length: ${value.length}, expected ${KEY_LENGTH}" }
        require(KEY_REGEX.toRegex().matches(value)) { "Key is invalid" }
    }

    override fun toString() = value

    companion object {
        const val KEY_LENGTH = 32
        const val KEY_REGEX = "^[a-zA-Z0-9]{$KEY_LENGTH}$"

        operator fun invoke(string: String): Key32? = runCatching {
            Key32(string)
        }.getOrNull()

        fun random() = SecureRandom().let { rng ->
            RandomStringGenerator.Builder()
                .selectFrom(*("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray()))
                .usingRandom(rng::nextInt)
                .build()
                .let { Key32(it.generate(KEY_LENGTH)) }
        }
    }
}

object Key32Serializer : KSerializer<Key32> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("io.airfoil.common.data.domain.dto.Key32Serializer", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Key32 =
        Key32(decoder.decodeString())

    override fun serialize(encoder: Encoder, value: Key32) {
        encoder.encodeString(value.toString())
    }
}
