package com.bhardwaj.pokemon.domain.repository

import com.bhardwaj.pokemon.domain.modal.Hero

interface LocalDataSource {
    suspend fun getSelectedHero(heroId: Int): Hero
}