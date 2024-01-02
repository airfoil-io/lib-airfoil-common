package io.airfoil.common.database.column

import io.airfoil.common.extension.enumByNameIgnoreCase
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.postgresql.util.PGobject
import org.postgresql.util.PSQLException

class PGEnum<T : Enum<T>>(enumTypeName: String, enumValue: T?) : PGobject() {
    init {
        value = enumValue?.name
        type = enumTypeName
    }
}

inline fun <reified T : Enum<T>> Table.enumerationByEnum(
    name: String,
    enumType: String,
): Column<T> {
    return this.customEnumeration(
        name,
        enumType,
        { value ->
            (value as String).enumByNameIgnoreCase<T>()
                ?: throw PSQLException("Invalid value $value for enum type $enumType", null)
        },
        { PGEnum(enumType, it) },
    )
}
