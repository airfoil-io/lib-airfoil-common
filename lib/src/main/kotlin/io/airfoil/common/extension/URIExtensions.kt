package io.airfoil.common.extension

import java.net.URI

fun URI.baseUri(): URI = URI.create(
    buildString {
        append("$scheme://$host")
        if (port != 80 && port != -1) {
            append(":$port")
        }
    }
)
