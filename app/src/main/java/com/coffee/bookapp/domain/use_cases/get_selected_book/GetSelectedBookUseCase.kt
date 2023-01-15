package com.coffee.bookapp.domain.use_cases.get_selected_book

import com.coffee.bookapp.data.repository.Repository
import com.coffee.bookapp.domain.model.Book

class GetSelectedBookUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(bookId: Int): Book {
        return repository.getSelectedBook(bookId = bookId)
    }
}