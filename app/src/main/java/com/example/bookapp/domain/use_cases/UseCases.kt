package com.example.bookapp.domain.use_cases

import com.example.bookapp.domain.use_cases.get_all_books.GetAllBooksUseCase
import com.example.bookapp.domain.use_cases.get_selected_book.GetSelectedBookUseCase
import com.example.bookapp.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.example.bookapp.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.example.bookapp.domain.use_cases.search_books.SearchBooksUseCase

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val getAllBooksUseCase: GetAllBooksUseCase,
    val searchBooksUseCase: SearchBooksUseCase,
    val getSelectedBookUseCase: GetSelectedBookUseCase
)