package io.airfoil.common.database.column

import io.airfoil.common.database.dialect.jsonbType
import io.airfoil.common.database.extension.psqlEscape
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ColumnType
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.statements.api.PreparedStatementApi
import org.jetbrains.exposed.sql.vendors.currentDialect
import org.postgresql.util.PGobject

inline fun <reified T : Any> Table.jsonb(
    name: String,
    kSerializer: KSerializer<T> = serializer(),
    json: Json = Json.Default,
): Column<T> {
    return this.jsonb(
        name = name,
        stringify = { json.encodeToString(kSerializer, it) },
        parse = { json.decodeFromString(kSerializer, it) },
    )
}

fun <T : Any> Table.jsonb(name: String, stringify: (T) -> String, parse: (String) -> T): Column<T> =
    registerColumn(name, JsonbColumnType(stringify, parse))

class JsonbColumnType<T : Any>(private val stringify: (T) -> String, private val parse: (String) -> T) : ColumnType() {
    override fun sqlType(): String = currentDialect.jsonbType()

    @Suppress("UNCHECKED_CAST")
    override fun setParameter(stmt: PreparedStatementApi, index: Int, value: Any?) {
        value?.run {
            stmt[index] = PGobject().apply {
                type = sqlType()
                this.value = stringify(value as T).psqlEscape()
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun valueFromDB(value: Any): T {
        if (value is PGobject) {
            return parse(value.value.toString())
        }
        return value as T
    }
}
