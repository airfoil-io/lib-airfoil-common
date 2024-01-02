package io.airfoil.common.extension

import com.auth0.jwt.interfaces.Claim

fun Claim.asStringOrNull(): String? = when (this.isNull()) {
    true -> null
    else -> this.asString()
}
