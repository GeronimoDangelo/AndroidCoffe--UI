package com.coffee.bookapp.data.repository

import com.coffee.bookapp.data.local.BookDatabase
import com.coffee.bookapp.domain.model.Book
import com.coffee.bookapp.domain.model.Jetpack
import com.coffee.bookapp.domain.model.XmlModel
import com.coffee.bookapp.domain.repository.LocalDataSource

class LocalDataSourceImpl(bookDatabase: BookDatabase) : LocalDataSource {

    private val bookDao = bookDatabase.bookDao()
    private val jetpackDao = bookDatabase.jetpackDao()
    private val xmlDao = bookDatabase.xmlDao()

    override suspend fun getSelectedBook(bookId: Int): Book {
        return bookDao.getSelectedBook(bookId = bookId)
    }

    override suspend fun getSelectedJetpack(jetId: Int): Jetpack {
        return jetpackDao.getSelectedJetpack(jetId = jetId)
    }

    override suspend fun getSelectedXml(xmlId: Int): XmlModel {
        return xmlDao.getSelectXml(xmlId = xmlId)
    }
}