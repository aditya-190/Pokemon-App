package com.bhardwaj.pokemon.data.remote

import com.bhardwaj.pokemon.domain.modal.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApi {
    @GET("/heroes")
    suspend fun getAllHeroes(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 3
    ): ApiResponse

    @GET("/searchHero")
    suspend fun searchHeroes(
        @Query("name") name: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 3
    ): ApiResponse
}