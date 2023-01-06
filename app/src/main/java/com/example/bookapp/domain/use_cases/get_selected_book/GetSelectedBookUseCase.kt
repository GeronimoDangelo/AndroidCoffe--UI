package com.example.bookapp.domain.use_cases.get_selected_book

import com.example.bookapp.data.repository.Repository
import com.example.bookapp.domain.model.Book

class GetSelectedBookUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(bookId: Int): Book {
        return repository.getSelectedBook(bookId = bookId)
    }
}