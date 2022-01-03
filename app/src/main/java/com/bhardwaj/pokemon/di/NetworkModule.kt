package com.bhardwaj.pokemon.di

import androidx.paging.ExperimentalPagingApi
import com.bhardwaj.pokemon.data.local.PokemonDatabase
import com.bhardwaj.pokemon.data.remote.PokemonApi
import com.bhardwaj.pokemon.data.repository.RemoteDataSourceImpl
import com.bhardwaj.pokemon.domain.repository.RemoteDataSource
import com.bhardwaj.pokemon.utils.Constants.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ExperimentalPagingApi
@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        val contentType = MediaType.get("application/json")

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun providePokemonApi(retrofit: Retrofit): PokemonApi {
        return retrofit.create(PokemonApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        pokemonApi: PokemonApi,
        pokemonDatabase: PokemonDatabase
    ): RemoteDataSource {
        return RemoteDataSourceImpl(
            pokemonApi = pokemonApi,
            pokemonDatabase = pokemonDatabase
        )
    }
}