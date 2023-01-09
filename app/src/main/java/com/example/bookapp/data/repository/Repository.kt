package com.example.bookapp.data.repository

import androidx.paging.PagingData
import com.example.bookapp.domain.model.Book
import com.example.bookapp.domain.model.Jetpack
import com.example.bookapp.domain.model.XmlModel
import com.example.bookapp.domain.repository.DataStoreOperations
import com.example.bookapp.domain.repository.LocalDataSource
import com.example.bookapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource,
    private val dataStore: DataStoreOperations
) {

    fun getAllBooks(): Flow<PagingData<Book>> {
        return remote.getAllBooks()
    }

    fun searchBooks(query: String): Flow<PagingData<Book>> {
        return remote.searchBooks(query = query)
    }

    suspend fun getSelectedBook(bookId: Int): Book {
        return local.getSelectedBook(bookId = bookId)
    }

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed = completed)
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }

    fun getAllJetpacks(): Flow<PagingData<Jetpack>> {
        return remote.getAllJetpacks()
    }

    fun searchJetpack(query: String): Flow<PagingData<Jetpack>> {
        return remote.searchJetpack(query = query)
    }

    suspend fun getSelectedJetpack(jetId: Int): Jetpack {
        return local.getSelectedJetpack(jetId = jetId)
    }

    fun getAllXmls(): Flow<PagingData<XmlModel>> {
        return remote.getAllXmls()
    }


    fun searchXml(query: String): Flow<PagingData<XmlModel>> {
        return remote.searchXmls(query = query)
    }


    suspend fun getSelectedXml(xmlId: Int): XmlModel {
        return local.getSelectedXml(xmlId = xmlId)
    }


}