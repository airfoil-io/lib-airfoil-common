package io.airfoil.common.database.extension

fun String.psqlEscape(): String =
    replace(Regex("(?<!')(')(?!')"), "''")
