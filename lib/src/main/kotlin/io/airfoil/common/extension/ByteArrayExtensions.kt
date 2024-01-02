package io.airfoil.common.extension

import java.security.MessageDigest

fun ByteArray.calculateHash(algorithm: String): String =
    MessageDigest.getInstance(algorithm)
        .digest(this)
        .fold("", { str, it -> str + "%02x".format(it) })

fun ByteArray.calculateSHA256(): String =
    calculateHash("SHA-256")

fun ByteArray.calculateMD5(): String =
    calculateHash("MD5")
