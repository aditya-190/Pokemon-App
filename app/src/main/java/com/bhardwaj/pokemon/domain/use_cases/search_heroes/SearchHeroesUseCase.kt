package com.bhardwaj.pokemon.domain.use_cases.search_heroes

import androidx.paging.PagingData
import com.bhardwaj.pokemon.data.repository.Repository
import com.bhardwaj.pokemon.domain.modal.Hero
import kotlinx.coroutines.flow.Flow

class SearchHeroesUseCase(
    private val repository: Repository
) {
    operator fun invoke(nameQuery: String): Flow<PagingData<Hero>> {
        return repository.searchHeroes(nameQuery = nameQuery)
    }
}