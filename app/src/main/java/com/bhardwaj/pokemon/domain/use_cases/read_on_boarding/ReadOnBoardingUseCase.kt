package com.bhardwaj.pokemon.domain.use_cases.read_on_boarding

import com.bhardwaj.pokemon.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<Boolean> {
        return repository.readOnBoardingState()
    }
}