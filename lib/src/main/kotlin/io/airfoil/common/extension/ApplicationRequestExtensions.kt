package io.airfoil.common.extension

import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.request.*

fun ApplicationRequest.parseBearerToken() = parseAuthorizationHeader()?.render()?.removePrefix("Bearer ")
