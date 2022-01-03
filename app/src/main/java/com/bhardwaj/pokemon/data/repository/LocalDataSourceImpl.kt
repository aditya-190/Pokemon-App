package com.bhardwaj.pokemon.data.repository

import com.bhardwaj.pokemon.data.local.PokemonDatabase
import com.bhardwaj.pokemon.domain.modal.Hero
import com.bhardwaj.pokemon.domain.repository.LocalDataSource

class LocalDataSourceImpl(pokemonDatabase: PokemonDatabase) : LocalDataSource {

    private val heroDao = pokemonDatabase.heroDao()

    override suspend fun getSelectedHero(heroId: Int): Hero {
        return heroDao.getSelectedHero(heroId = heroId)
    }
}