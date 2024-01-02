package io.airfoil.common.exception.dto

import io.airfoil.common.exception.ErrorCategory
import io.airfoil.common.exception.ErrorCategories
import io.airfoil.common.exception.AirfoilException
import io.ktor.http.*
import kotlinx.serialization.Serializable

@Serializable
data class AirfoilErrorResponse(
    val category: ErrorCategory,
    val statusCode: Int,
    val message: String,
    val displayMessage: String? = null,

) {

    companion object {
        fun from(cause: Throwable, statusCode: HttpStatusCode) = AirfoilErrorResponse(
            category = cause.errorCategoryOrUnknown(),
            statusCode = statusCode.value,
            message = cause.message ?: "(no message)",
            displayMessage = cause.displayMessageOrNull(),
        )
    }
}

private fun Throwable.errorCategoryOrUnknown() = when (this) {
    is AirfoilException -> category
    else -> ErrorCategories.UNKNOWN
}

private fun Throwable.displayMessageOrNull() = when (this) {
    is AirfoilException -> displayMessage
    else -> null
}
