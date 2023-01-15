package com.coffee.bookapp.domain.use_cases.get_all_books

import androidx.paging.PagingData
import com.coffee.bookapp.data.repository.Repository
import com.coffee.bookapp.domain.model.Book
import kotlinx.coroutines.flow.Flow

class GetAllBooksUseCase(
    private val repository: Repository
) {
    operator fun invoke (): Flow<PagingData<Book>>{
        return repository.getAllBooks()
    }
}