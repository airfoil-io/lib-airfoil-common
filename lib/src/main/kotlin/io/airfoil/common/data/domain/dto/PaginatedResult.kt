package io.airfoil.common.data.domain.dto

import kotlinx.serialization.Serializable

@Serializable
data class PaginatedResult<T> (
    val data: List<T> = emptyList(),
    val limit: Int,
    val nextOffset: Long? = null,
) {
    companion object {
        /*
            Generate a paginated result from the dataset, the limit and the current offset.

            NOTE: When calculating the nextOffset, the dataset is expected to be able to
            contain at least 1 more record than the limit. This is to allow us to nullify
            the nextOffset when there are no more records that can be retrieved. As a result,
            queries should request a limit of at least 1 more than the requested limit and
            the results should be passed here.
         */
        fun <T> paginate(
            data: List<T>,
            limit: Int,
            currentOffset: Long,
        ) = PaginatedResult<T>(
            data = data.take(limit),
            limit = limit,
            nextOffset = when (data.size > limit) {
                true -> currentOffset + limit
                else -> null
            },
        )
    }
}
