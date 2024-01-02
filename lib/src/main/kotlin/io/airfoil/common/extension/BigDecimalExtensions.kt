package io.airfoil.common.extension

import java.math.BigDecimal

fun Double.toBigDecimal() = BigDecimal(this)
fun Float.toBigDecimal() = BigDecimal(this.toDouble())
fun Int.toBigDecimal() = BigDecimal(this)
fun Long.toBigDecimal() = BigDecimal(this)
