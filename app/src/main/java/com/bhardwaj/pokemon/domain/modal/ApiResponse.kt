package com.bhardwaj.pokemon.domain.modal

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val success: Boolean,
    val message: String? = null,
    val totalHeroes: Int? = null,
    val returnedHeroes: Int? = null,
    val previousPage: Int? = null,
    val nextPage: Int? = null,
    val lastUpdated: Long? = null,
    val heroes: List<Hero> = emptyList()
)
