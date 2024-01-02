package io.airfoil.common.exception

import kotlinx.serialization.Serializable
import kotlin.runCatching

@JvmInline
@Serializable
value class ErrorCategory(val value: String) {
    init {
        require(value.isNotEmpty()) { "ErrorCategory cannot be empty" }
    }

    override fun toString() = value.toString()

    companion object {
        operator fun invoke(string: String): ErrorCategory? = runCatching {
            ErrorCategory(string)
        }.getOrNull()
    }
}
