package com.bhardwaj.pokemon.data.repository

import androidx.paging.PagingData
import com.bhardwaj.pokemon.domain.modal.Hero
import com.bhardwaj.pokemon.domain.repository.DataStoreOperations
import com.bhardwaj.pokemon.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remote: RemoteDataSource,
    private val dataStore: DataStoreOperations
) {

    fun getAllHeroes(): Flow<PagingData<Hero>> {
        return remote.getAllHeroes()
    }

    fun searchHeroes(nameQuery: String): Flow<PagingData<Hero>> {
        return remote.searchHeroes(nameQuery = nameQuery)
    }

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed = completed)
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }
}