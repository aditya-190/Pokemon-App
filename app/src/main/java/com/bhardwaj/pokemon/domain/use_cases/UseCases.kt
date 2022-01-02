package com.bhardwaj.pokemon.domain.use_cases

import com.bhardwaj.pokemon.domain.use_cases.read_on_boarding.ReadOnBoardingUseCase
import com.bhardwaj.pokemon.domain.use_cases.save_on_boarding.SaveOnBoardingUseCase

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase
)
