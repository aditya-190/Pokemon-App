package com.bhardwaj.pokemon.di

import android.content.Context
import com.bhardwaj.pokemon.data.repository.DataStoreOperationsImpl
import com.bhardwaj.pokemon.data.repository.Repository
import com.bhardwaj.pokemon.domain.repository.DataStoreOperations
import com.bhardwaj.pokemon.domain.use_cases.UseCases
import com.bhardwaj.pokemon.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.bhardwaj.pokemon.domain.use_cases.read_on_boarding.ReadOnBoardingUseCase
import com.bhardwaj.pokemon.domain.use_cases.save_on_boarding.SaveOnBoardingUseCase
import com.bhardwaj.pokemon.domain.use_cases.search_heroes.SearchHeroesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext context: Context
    ): DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository),
            getAllHeroesUseCase = GetAllHeroesUseCase(repository),
            searchHeroesUseCase = SearchHeroesUseCase(repository)
        )
    }
}