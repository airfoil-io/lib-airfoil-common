package io.airfoil.common.extension

import org.mindrot.jbcrypt.BCrypt

private const val DEFAULT_BCRYPT_SALT_COST = 10

inline fun <reified T : Enum<T>> String.enumByNameIgnoreCase(default: T? = null): T? {
    return enumValues<T>().firstOrNull { it.name.equals(this, true) } ?: default
}

fun String.nullIfEmpty(): String? = when (this.isEmpty()) {
    true -> null
    else -> this
}

fun String.isLowerCase(): Boolean = 
    (this.count { it.isLowerCase() || it.isWhitespace() || it.isDigit() } == this.length)

fun String.isUpperCase(): Boolean = 
    (this.count { it.isUpperCase() || it.isWhitespace() || it.isDigit() } == this.length)

fun String.calculateHash(algorithm: String): String =
    this.toByteArray().calculateHash(algorithm)

fun String.calculateSHA256(): String =
    this.toByteArray().calculateSHA256()

fun String.calculateMD5(): String =
    this.toByteArray().calculateMD5()

fun String.toHash(cost: Int = DEFAULT_BCRYPT_SALT_COST): String =
    BCrypt.hashpw(this, BCrypt.gensalt(cost))
