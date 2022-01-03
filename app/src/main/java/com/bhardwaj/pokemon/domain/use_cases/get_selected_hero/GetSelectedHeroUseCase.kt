package com.bhardwaj.pokemon.domain.use_cases.get_selected_hero

import com.bhardwaj.pokemon.data.repository.Repository
import com.bhardwaj.pokemon.domain.modal.Hero

class GetSelectedHeroUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(heroId: Int): Hero {
        return repository.getSelectedHero(heroId = heroId)
    }
}