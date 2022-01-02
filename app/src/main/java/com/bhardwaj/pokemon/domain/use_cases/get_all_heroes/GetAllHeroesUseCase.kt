package com.bhardwaj.pokemon.domain.use_cases.get_all_heroes

import androidx.paging.PagingData
import com.bhardwaj.pokemon.data.repository.Repository
import com.bhardwaj.pokemon.domain.modal.Hero
import kotlinx.coroutines.flow.Flow

class GetAllHeroesUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<Hero>> {
        return repository.getAllHeroes()
    }
}