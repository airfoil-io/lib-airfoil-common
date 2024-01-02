package io.airfoil.common.data.domain.dto

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.mindrot.jbcrypt.BCrypt
import kotlin.text.Regex

@JvmInline
@Serializable(with = PasswordSerializer::class)
value class Password(val value: String) {

    init {
        require(value.isNotEmpty()) { "Password cannot be empty" }
        require(PASSWORD_REGEX.toRegex().matches(value)) {
            "Password must be at least 8 characters, with at least one uppercase letter, one lowercase letter and one number" 
        }
    }

    override fun toString() = ""

    companion object {
        const val PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z:;\\!\\@\\#\\$\\%\\^\\&\\*\\~\\?\\_\\+\\-\\/\\=\\.\\d]{8,}$"

        operator fun invoke(string: String): Password? = runCatching {
            Password(string)
        }.getOrNull()
    }

    fun toHash(): String {
        return BCrypt.hashpw(value, BCrypt.gensalt(10))
    }

    fun validate(passwordHash: String): Boolean =
        BCrypt.checkpw(value, passwordHash)
}

object PasswordSerializer : KSerializer<Password> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("io.airfoil.common.data.domain.dto.PasswordSerializer", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Password =
        Password(decoder.decodeString())

    override fun serialize(encoder: Encoder, value: Password) {
        encoder.encodeString("")
    }
}
