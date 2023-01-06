package com.example.bookapp.presentation.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.bookapp.domain.model.Book
import com.example.bookapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val usesCases: UseCases
) : ViewModel() {
    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery


    private val _searchedBooks = MutableStateFlow<PagingData<Book>>(PagingData.empty())
    val searchedBooks = _searchedBooks

    fun searchBooks(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            usesCases.searchBooksUseCase(query = query).cachedIn(viewModelScope).collect{
                _searchedBooks.value = it
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }
}