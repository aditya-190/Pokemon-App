package com.bhardwaj.pokemon.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bhardwaj.pokemon.data.remote.PokemonApi
import com.bhardwaj.pokemon.domain.modal.Hero
import javax.inject.Inject

class SearchHeroSource @Inject constructor(
    private val pokemonApi: PokemonApi,
    private val nameQuery: String,
    private val page: Int,
    private val limit: Int,
) : PagingSource<Int, Hero>() {

    override fun getRefreshKey(state: PagingState<Int, Hero>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hero> {
        return try {
            val apiResponse = pokemonApi.searchHeroes(name = nameQuery, page = page, limit = limit)
            val heroes = apiResponse.heroes
            if (heroes.isNotEmpty()) {
                LoadResult.Page(
                    data = heroes,
                    prevKey = apiResponse.previousPage,
                    nextKey = apiResponse.nextPage
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}