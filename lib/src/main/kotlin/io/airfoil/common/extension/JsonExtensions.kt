package io.airfoil.common.extension

import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

inline fun <reified T> String.decode(): T = Json.decodeFromString<T>(this)

inline fun <reified T> T.encode(): String = Json.encodeToString(this)
