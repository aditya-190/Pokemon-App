package com.bhardwaj.pokemon.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bhardwaj.pokemon.data.local.PokemonDatabase
import com.bhardwaj.pokemon.data.paging_source.HeroRemoteMediator
import com.bhardwaj.pokemon.data.paging_source.SearchHeroSource
import com.bhardwaj.pokemon.data.remote.PokemonApi
import com.bhardwaj.pokemon.domain.modal.Hero
import com.bhardwaj.pokemon.domain.repository.RemoteDataSource
import com.bhardwaj.pokemon.utils.Constants.HEROES_PER_PAGES_SEARCH_HEROES
import com.bhardwaj.pokemon.utils.Constants.PAGES_PER_REQUEST_GET_HEROES
import com.bhardwaj.pokemon.utils.Constants.PAGES_PER_REQUEST_SEARCH_HEROES
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
            config = PagingConfig(pageSize = PAGES_PER_REQUEST_GET_HEROES),
            remoteMediator = HeroRemoteMediator(
                pokemonApi = pokemonApi,
                pokemonDatabase = pokemonDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchHeroes(nameQuery: String): Flow<PagingData<Hero>> {
        return Pager(
            config = PagingConfig(pageSize = PAGES_PER_REQUEST_SEARCH_HEROES),
            remoteMediator = HeroRemoteMediator(
                pokemonApi = pokemonApi,
                pokemonDatabase = pokemonDatabase
            ),
            pagingSourceFactory = {
                SearchHeroSource(
                    pokemonApi = pokemonApi,
                    nameQuery = nameQuery,
                    page = PAGES_PER_REQUEST_SEARCH_HEROES,
                    limit = HEROES_PER_PAGES_SEARCH_HEROES
                )
            }
        ).flow
    }
}