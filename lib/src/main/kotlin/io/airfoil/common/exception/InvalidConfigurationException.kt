package io.airfoil.common.exception

open class InvalidConfigurationException(
    message: String,
    displayMessage: String = message,
) : AirfoilException(
    message = message,
    displayMessage = displayMessage,
    category = ErrorCategories.CONFIG,
)
