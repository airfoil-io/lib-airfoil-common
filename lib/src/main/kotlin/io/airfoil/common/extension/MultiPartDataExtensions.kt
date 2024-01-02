package io.airfoil.common.extension

import io.ktor.http.content.*

suspend fun MultiPartData.find(predicate: (PartData) -> Boolean): PartData? {
    var foundPartData: PartData? = null
    this.forEachPart { part ->
        if (foundPartData == null && predicate(part)) {
            foundPartData = part
        }
    }
    return foundPartData
}
