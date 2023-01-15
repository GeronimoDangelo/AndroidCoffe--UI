package com.coffee.bookapp.domain.repository

import com.coffee.bookapp.domain.model.Book
import com.coffee.bookapp.domain.model.Jetpack
import com.coffee.bookapp.domain.model.XmlModel

interface LocalDataSource {
    suspend fun getSelectedBook(bookId: Int): Book
    suspend fun getSelectedJetpack(jetId: Int): Jetpack
    suspend fun getSelectedXml(xmlId: Int): XmlModel
}