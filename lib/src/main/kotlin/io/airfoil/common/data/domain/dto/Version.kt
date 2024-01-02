package io.airfoil.common.data.domain.dto

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.runCatching

@Serializable(with = VersionSerializer::class)
class Version(val value: String) {
    private val major: Int
    private val minor: Int
    private val build: Int

    init {
        major = value.extractComponent(0)
        minor = value.extractComponent(1)
        build = value.extractComponent(2)

        require(major >= 0 && major <= MAX_COMPONENT_VALUE) { "Version major component out of bounds" }
        require(minor >= 0 && minor <= MAX_COMPONENT_VALUE) { "Version minor component out of bounds" }
        require(build >= 0 && build <= MAX_COMPONENT_VALUE) { "Version build component out of bounds" }
    }

    override fun toString() = "$major.$minor.$build"

    fun toInt() = (major * MAJOR_INT_FACTOR) + (minor * MINOR_INT_FACTOR) + (build * BUILD_INT_FACTOR)

    companion object {
        const val MAX_COMPONENT_VALUE = 999

        const val MAJOR_INT_FACTOR = 1000000
        const val MINOR_INT_FACTOR = 1000
        const val BUILD_INT_FACTOR = 1

        operator fun invoke(string: String): Version? = runCatching {
            Version(string)
        }.getOrNull()
    }

    private fun String.extractComponent(index: Int): Int =
        this.split(".").getOrNull(index)?.toIntOrNull() 
            ?: throw IllegalArgumentException("Invalid version string: $value")
}

object VersionSerializer : KSerializer<Version> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("io.airfoil.common.data.domain.dto.VersionSerializer", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Version =
        Version(decoder.decodeString())

    override fun serialize(encoder: Encoder, value: Version) {
        encoder.encodeString(value.toString())
    }
}
