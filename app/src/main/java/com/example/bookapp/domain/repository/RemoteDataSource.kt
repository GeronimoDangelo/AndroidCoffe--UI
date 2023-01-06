package com.example.bookapp.domain.repository

import androidx.paging.PagingData
import com.example.bookapp.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllBooks(): Flow<PagingData<Book>>
    fun searchBooks(query:String):Flow<PagingData<Book>>
}