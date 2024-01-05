package io.airfoil.common.exception

open class UnexpectedStateException(
    message: String,
    displayMessage: String = message,
    category: ErrorCategory,
) : AirfoilException(
    message = message,
    displayMessage = displayMessage,
    category = category,
)
