package com.bhardwaj.pokemon.domain.repository

import androidx.paging.PagingData
import com.bhardwaj.pokemon.domain.modal.Hero
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllHeroes(): Flow<PagingData<Hero>>
    fun searchHeroes(): Flow<PagingData<Hero>>
}