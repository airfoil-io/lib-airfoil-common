package io.airfoil.common.exception

open class AirfoilException(
    message: String? = null,
    cause: Throwable? = null,
    val category: ErrorCategory,
    val displayMessage: String? = null,
) : Exception(message, cause)
