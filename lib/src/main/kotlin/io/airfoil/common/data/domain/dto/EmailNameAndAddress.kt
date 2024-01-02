package io.airfoil.common.data.domain.dto

import kotlinx.serialization.Serializable

@Serializable
data class EmailNameAndAddress(
    val name: String,
    val address: EmailAddress,
)
