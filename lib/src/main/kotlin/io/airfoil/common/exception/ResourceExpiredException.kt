package io.airfoil.common.exception

open class ResourceExpiredException(
    message: String,
    displayMessage: String = message,
    category: ErrorCategory,
) : AirfoilException(
    message = message,
    displayMessage = displayMessage,
    category = category,
)
