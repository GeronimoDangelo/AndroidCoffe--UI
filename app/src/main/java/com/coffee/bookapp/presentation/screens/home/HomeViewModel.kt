package com.coffee.bookapp.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.coffee.bookapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    useCases: UseCases
) : ViewModel() {
        val getAllBooks = useCases.getAllBooksUseCase()
}