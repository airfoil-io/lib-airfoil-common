package io.airfoil.common.util

fun requireOrThrow(value: Boolean, customException: () -> Throwable) {
    if (!value) {
        throw customException()
    }
}
