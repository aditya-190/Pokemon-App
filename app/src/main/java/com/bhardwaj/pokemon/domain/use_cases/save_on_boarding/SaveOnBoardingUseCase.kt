package com.bhardwaj.pokemon.domain.use_cases.save_on_boarding

import com.bhardwaj.pokemon.data.repository.Repository

class SaveOnBoardingUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(completed: Boolean) {
        repository.saveOnBoardingState(completed = completed)
    }
}