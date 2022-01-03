package com.bhardwaj.pokemon.presentation.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bhardwaj.pokemon.domain.modal.Hero
import com.bhardwaj.pokemon.domain.use_cases.UseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {
    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _searchedHeroes = MutableStateFlow<PagingData<Hero>>(PagingData.empty())
    val searchedHeroes = _searchedHeroes

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchHeroes(nameQuery: String) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.searchHeroesUseCase(nameQuery = nameQuery).cachedIn(viewModelScope).collect {
                _searchedHeroes.value = it
            }
        }
    }
}