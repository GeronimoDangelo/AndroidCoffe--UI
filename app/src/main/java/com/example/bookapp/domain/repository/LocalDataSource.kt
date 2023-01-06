package com.example.bookapp.domain.repository

import com.example.bookapp.domain.model.Book

interface LocalDataSource {
    suspend fun getSelectedBook(bookId: Int): Book
}