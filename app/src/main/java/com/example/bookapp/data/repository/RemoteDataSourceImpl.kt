package com.example.bookapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.bookapp.data.local.BookDatabase
import com.example.bookapp.data.paging_source.BookRemoteMediator
import com.example.bookapp.data.paging_source.JetpackRemoteMediator
import com.example.bookapp.data.paging_source.SearchBooksSource
import com.example.bookapp.data.paging_source.SearchJetpackSource
import com.example.bookapp.data.remote.BookApi
import com.example.bookapp.domain.model.Book
import com.example.bookapp.domain.model.Jetpack
import com.example.bookapp.domain.repository.RemoteDataSource
import com.example.bookapp.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val bookApi: BookApi,
    private val bookDatabase: BookDatabase
) : RemoteDataSource {

    private val bookDao = bookDatabase.bookDao()
    private val jetpackDao = bookDatabase.jetpackDao()

    override fun getAllBooks(): Flow<PagingData<Book>> {
        val pagingSourceFactory = { bookDao.getAllBooks() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = BookRemoteMediator(
                bookApi = bookApi,
                bookDatabase = bookDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchBooks(query: String): Flow<PagingData<Book>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchBooksSource(bookApi = bookApi, query = query)
            }
        ).flow
    }

    override fun getAllJetpacks(): Flow<PagingData<Jetpack>> {
        val pagingSourceFactory = { jetpackDao.getAllJetpacks() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = JetpackRemoteMediator(
                bookApi = bookApi,
                bookDatabase = bookDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchJetpack(query: String): Flow<PagingData<Jetpack>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchJetpackSource(bookApi = bookApi, query = query)
            }
        ).flow
    }
}