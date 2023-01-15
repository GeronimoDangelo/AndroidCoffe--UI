package com.coffee.bookapp.domain.repository

import androidx.paging.PagingData
import com.coffee.bookapp.domain.model.Book
import com.coffee.bookapp.domain.model.Jetpack
import com.coffee.bookapp.domain.model.XmlModel
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllBooks(): Flow<PagingData<Book>>
    fun searchBooks(query:String):Flow<PagingData<Book>>
    fun getAllJetpacks(): Flow<PagingData<Jetpack>>
    fun searchJetpack(query: String): Flow<PagingData<Jetpack>>
    fun getAllXmls(): Flow<PagingData<XmlModel>>
    fun searchXmls(query:String): Flow<PagingData<XmlModel>>
}