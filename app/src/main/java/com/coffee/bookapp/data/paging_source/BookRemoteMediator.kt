package com.coffee.bookapp.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.coffee.bookapp.data.local.BookDatabase
import com.coffee.bookapp.data.remote.BookApi
import com.coffee.bookapp.domain.model.Book
import com.coffee.bookapp.domain.model.BookRemoteKeys
import javax.inject.Inject
import java.lang.Exception

@ExperimentalPagingApi
class BookRemoteMediator (
    private val bookApi: BookApi,
    private val bookDatabase: BookDatabase
) : RemoteMediator<Int, Book>() {


    private val bookDao = bookDatabase.bookDao()
    private val bookRemoteKeysDao = bookDatabase.bookRemoteKeysDao()

    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = bookRemoteKeysDao.getRemoteKeys(bookId = 1)?.lastUpdated ?: 0
        val cacheTimeout = 1440

        val diffInMinutes = (currentTime - lastUpdated) / 1000 / 60
        return if (diffInMinutes.toInt() <= cacheTimeout) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Book>): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextPage
                }
            }


            val response = bookApi.getAllBooks(page = page)
            if (response.books.isNotEmpty()) {
                bookDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        bookDao.deleteAllBooks()
                        bookRemoteKeysDao.deleteAllRemoteKeys()
                    }
                    val prevPage = response.prevPage
                    val nextPage = response.nextPage
                    val keys = response.books.map { book ->
                        BookRemoteKeys(
                            id = book.id,
                            prevPage = prevPage,
                            nextPage = nextPage,
                            lastUpdated = response.lastUpdated
                        )
                    }
                    bookRemoteKeysDao.addAllRemoteKeys(bookRemoteKeys = keys)
                    bookDao.addBooks(books = response.books)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)

        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }


    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Book>
    ): BookRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                bookRemoteKeysDao.getRemoteKeys(bookId = id)
            }
        }
    }


    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Book>
    ): BookRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { book ->
                bookRemoteKeysDao.getRemoteKeys(bookId = book.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Book>): BookRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { book ->
                bookRemoteKeysDao.getRemoteKeys(bookId = book.id)
            }
    }
}