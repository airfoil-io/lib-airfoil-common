package io.airfoil.common.data.domain.dto

import kotlinx.serialization.Serializable
import org.apache.commons.validator.routines.UrlValidator
import java.net.URI

@JvmInline
@Serializable
value class Url(val value: String) {
    
    init {
        require(value.isNotEmpty()) { "URL cannot be empty" }
        require(UrlValidator().isValid(value)) { "URL is invalid" }
    }

    override fun toString() = value.toString()

    fun toURI() = URI.create(value)

    companion object {
        operator fun invoke(string: String): Url? = runCatching {
            Url(string)
        }.getOrNull()
    }
}
