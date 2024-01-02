package io.airfoil.common.data.domain.dto

import kotlinx.serialization.Serializable

@Serializable
data class PersonName(
    val firstName: String,
    val middleName: String? = null,
    val lastName: String,
) {

    init {
        require(firstName.isNotEmpty()) { "First name cannot be empty" }
        require(lastName.isNotEmpty()) { "Last name cannot be empty" }
    }

    val displayName: String
        get() = when (middleName) {
            null -> "$firstName $lastName"
            else -> "$firstName $middleName $lastName"
        }
}
