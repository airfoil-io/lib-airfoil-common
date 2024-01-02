package io.airfoil.common.extension

import java.io.ByteArrayOutputStream
import java.io.PrintStream

fun buildMessageWithLogMetadata(message: String, parameters: List<Pair<String, Any?>> = emptyList()) = buildString {
    val hasList = parameters.isNotEmpty()
    if (message.isNotBlank()) {
        append(message)
        if (hasList) {
            append(" ")
        }
    }
    if (hasList) {
        append("[")
        append(parameters.joinToString { (name, value) -> "$name: $value" })
        append("]")
    }
}

fun buildMessageWithLogException(message: String, t: Throwable) = buildString {
    if (message.isNotBlank()) {
        append(message)
    }
    val stream = ByteArrayOutputStream()
    t.printStackTrace(PrintStream(stream))
    append(stream.toString("UTF-8"))
}

fun buildMessageWithLogMetadata(message: String, vararg parameters: Pair<String, Any?>) =
    buildMessageWithLogMetadata(message, parameters.toList())

fun String.withLogMetadata(vararg parameters: Pair<String, Any?>) =
    buildMessageWithLogMetadata(this, parameters.toList())

fun String.withLogMetadata(parameters: List<Pair<String, Any?>>) =
    buildMessageWithLogMetadata(this, parameters.toList())

fun String.withLogException(t: Throwable) =
    buildMessageWithLogException(this, t)
