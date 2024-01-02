package io.airfoil.common.extension

import org.apache.commons.lang3.exception.ExceptionUtils

fun Throwable.rootCause(): Throwable =
    ExceptionUtils.getRootCause(this)
