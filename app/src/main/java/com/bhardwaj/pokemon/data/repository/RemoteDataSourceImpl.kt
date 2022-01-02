package com.bhardwaj.pokemon.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bhardwaj.pokemon.data.local.PokemonDatabase
import com.bhardwaj.pokemon.data.paging_source.HeroRemoteMediator
import com.bhardwaj.pokemon.data.remote.PokemonApi
import com.bhardwaj.pokemon.domain.modal.Hero
import com.bhardwaj.pokemon.domain.repository.RemoteDataSource
import com.bhardwaj.pokemon.utils.Constants.DEFAULT_PAGING_LIMIT
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val pokemonApi: PokemonApi,
    private val pokemonDatabase: PokemonDatabase
) : RemoteDataSource {

    private val heroDao = pokemonDatabase.heroDao()

    override fun getAllHeroes(): Flow<PagingData<Hero>> {
        val pagingSourceFactory = { heroDao.getAllHeroes() }
        return Pager(
            config = PagingConfig(pageSize = DEFAULT_PAGING_LIMIT),
            remoteMediator = HeroRemoteMediator(
                pokemonApi = pokemonApi,
                pokemonDatabase = pokemonDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchHeroes(): Flow<PagingData<Hero>> {
        TODO("Not yet implemented")
    }
}