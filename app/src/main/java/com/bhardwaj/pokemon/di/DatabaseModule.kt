package com.bhardwaj.pokemon.di

import android.content.Context
import androidx.room.Room
import com.bhardwaj.pokemon.data.local.PokemonDatabase
import com.bhardwaj.pokemon.data.repository.LocalDataSourceImpl
import com.bhardwaj.pokemon.domain.repository.LocalDataSource
import com.bhardwaj.pokemon.utils.Constants.POKEMON_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, PokemonDatabase::class.java, POKEMON_DATABASE).build()

    @Provides
    @Singleton
    fun provideLocalDataSource(
        pokemonDatabase: PokemonDatabase
    ): LocalDataSource {
        return LocalDataSourceImpl(
            pokemonDatabase = pokemonDatabase
        )
    }
}