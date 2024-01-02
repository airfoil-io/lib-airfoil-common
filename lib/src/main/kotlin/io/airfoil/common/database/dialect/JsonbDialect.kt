package io.airfoil.common.database.dialect

import org.jetbrains.exposed.sql.vendors.DatabaseDialect
import org.jetbrains.exposed.sql.vendors.PostgreSQLDialect

fun DatabaseDialect.jsonbType(): String = when (this) {
    is PostgreSQLDialect -> "JSONB"
    else -> error("Must define jsonb data type for current dialect; ${this.name}")
}
