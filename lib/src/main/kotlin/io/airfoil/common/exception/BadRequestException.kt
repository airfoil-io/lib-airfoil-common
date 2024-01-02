package io.airfoil.common.exception

open class BadRequestException(
    message: String,
    displayMessage: String = message,
) : AirfoilException(
    message = message,
    displayMessage = displayMessage,
    category = ErrorCategories.REQUEST,
)
