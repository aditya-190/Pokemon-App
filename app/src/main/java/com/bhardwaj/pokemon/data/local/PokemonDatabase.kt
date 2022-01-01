package com.bhardwaj.pokemon.data.local

import androidx.room.Database
import androidx.room.TypeConverters
import com.bhardwaj.pokemon.data.local.dao.HeroDao
import com.bhardwaj.pokemon.data.local.dao.HeroRemoteKeyDao
import com.bhardwaj.pokemon.domain.modal.Hero
import com.bhardwaj.pokemon.domain.modal.HeroRemoteKey

@Database(entities = [Hero::class, HeroRemoteKey::class], version = 1)
@TypeConverters(DatabaseConvertor::class)
abstract class PokemonDatabase {
    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeyDao
}