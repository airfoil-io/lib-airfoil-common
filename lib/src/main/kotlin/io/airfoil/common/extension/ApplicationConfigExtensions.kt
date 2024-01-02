package io.airfoil.common.extension

import io.ktor.server.config.*

fun ApplicationConfig.configOrNull(path: String): ApplicationConfig? = try {
    this.config(path)
} catch (ex: ApplicationConfigurationException) {
    if (ex.localizedMessage.contains("not found")) {
        null
    } else {
        throw ex
    }
}

fun ApplicationConfig.stringValueOrNull(path: String): String? =
    propertyOrNull(path)?.getString()

fun ApplicationConfig.stringValueOrError(path: String, errorMessage: String): String =
    stringValueOrNull(path) ?: throw ApplicationConfigurationException(errorMessage)

fun ApplicationConfig.stringValueOrDefault(path: String, defaultValue: String): String =
    stringValueOrNull(path) ?: defaultValue

fun ApplicationConfig.intValueOrNull(path: String): Int? =
    propertyOrNull(path)?.getString()?.toInt()

fun ApplicationConfig.intValueOrError(path: String, errorMessage: String): Int =
    intValueOrNull(path) ?: throw ApplicationConfigurationException(errorMessage)

fun ApplicationConfig.intValueOrDefault(path: String, defaultValue: Int): Int =
    intValueOrNull(path) ?: defaultValue

fun ApplicationConfig.longValueOrNull(path: String): Long? =
    propertyOrNull(path)?.getString()?.toLong()

fun ApplicationConfig.longValueOrError(path: String, errorMessage: String): Long =
    longValueOrNull(path) ?: throw ApplicationConfigurationException(errorMessage)

fun ApplicationConfig.longValueOrDefault(path: String, defaultValue: Long): Long =
    longValueOrNull(path) ?: defaultValue

fun ApplicationConfig.boolValueOrNull(path: String): Boolean? =
    propertyOrNull(path)?.getString()?.toBoolean()

fun ApplicationConfig.boolValueOrError(path: String, errorMessage: String): Boolean =
    boolValueOrNull(path) ?: throw ApplicationConfigurationException(errorMessage)

fun ApplicationConfig.boolValueOrDefault(path: String, defaultValue: Boolean): Boolean =
    boolValueOrNull(path) ?: defaultValue

fun ApplicationConfig.doubleValueOrNull(path: String): Double? =
    propertyOrNull(path)?.getString()?.toDouble()

fun ApplicationConfig.doubleValueOrError(path: String, errorMessage: String): Double =
    doubleValueOrNull(path) ?: throw ApplicationConfigurationException(errorMessage)

fun ApplicationConfig.doubleValueOrDefault(path: String, defaultValue: Double): Double =
    doubleValueOrNull(path) ?: defaultValue

inline fun <reified T : Enum<T>> ApplicationConfig.enumValueOrNull(path: String): T? =
    stringValueOrNull(path)?.let { it.enumByNameIgnoreCase<T>() }

inline fun <reified T : Enum<T>> ApplicationConfig.enumValueOrError(path: String, errorMessage: String): T =
    enumValueOrNull<T>(path) ?: throw ApplicationConfigurationException(errorMessage)

inline fun <reified T : Enum<T>> ApplicationConfig.enumValueOrDefault(path: String, defaultValue: T): T =
    enumValueOrNull<T>(path) ?: defaultValue

fun ApplicationConfig.stringListOrNull(path: String): List<String>? =
    propertyOrNull(path)?.getList()

fun ApplicationConfig.stringListOrEmpty(path: String): List<String> =
    stringListOrNull(path) ?: emptyList()

fun ApplicationConfig.stringListOrError(path: String, errorMessage: String): List<String> =
    stringListOrNull(path) ?: throw ApplicationConfigurationException(errorMessage)

fun ApplicationConfig.stringListOrDefault(path: String, defaultValue: List<String>): List<String> =
    stringListOrNull(path) ?: defaultValue
