package com.example.bookapp.domain.repository

import com.example.bookapp.domain.model.Book
import com.example.bookapp.domain.model.Jetpack
import com.example.bookapp.domain.model.XmlModel

interface LocalDataSource {
    suspend fun getSelectedBook(bookId: Int): Book
    suspend fun getSelectedJetpack(jetId: Int): Jetpack
    suspend fun getSelectedXml(xmlId: Int): XmlModel
}