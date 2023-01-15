package com.coffee.bookapp.presentation.screens.jetpacksearch

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.coffee.bookapp.domain.model.Book
import com.coffee.bookapp.domain.model.Jetpack
import com.coffee.bookapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchJetpackViewModel@Inject constructor(
        private val usesCases: UseCases
): ViewModel() {


    private val _searchQueries = mutableStateOf("")
    val searchQueries = _searchQueries

    private val _searchedJetpacks = MutableStateFlow<PagingData<Jetpack>>(PagingData.empty())
    val searchedJetpacks = _searchedJetpacks

    fun searchJetpacks(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            usesCases.searchJetpacksUseCase(query = query).cachedIn(viewModelScope).collect{
                _searchedJetpacks.value = it
            }
        }
    }

    fun updateSearchQueries(query: String) {
        _searchQueries.value = query
    }
}