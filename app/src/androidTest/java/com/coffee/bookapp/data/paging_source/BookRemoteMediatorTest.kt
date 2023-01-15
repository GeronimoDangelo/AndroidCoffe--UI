@file:OptIn(ExperimentalPagingApi::class)

package com.coffee.bookapp.data.paging_source

import androidx.paging.*
import androidx.paging.RemoteMediator.*
import androidx.test.core.app.ApplicationProvider
import com.example.bookapp.data.local.BookDatabase
import com.coffee.bookapp.data.remote.FakeBookApi2
import com.example.bookapp.domain.model.Book
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class BookRemoteMediatorTest {

    private lateinit var bookApi2: com.coffee.bookapp.data.remote.FakeBookApi2
    private lateinit var bookDatabase: BookDatabase

    @Before
    fun setup() {
        bookApi2 = com.coffee.bookapp.data.remote.FakeBookApi2()
        bookDatabase = BookDatabase.create(
            context = ApplicationProvider.getApplicationContext(),
            useInMemory = true
        )
    }

    @After
    fun cleanUp() {
        bookDatabase.clearAllTables()
    }

    @Test
    fun refresh_load_success_and_end_of_pagination_true_when_no_more_data() =
        runBlocking {
            bookApi2.cleanUp()
            val remoteMediator = com.coffee.bookapp.data.paging_source.BookRemoteMediator(
                bookApi = bookApi2,
                bookDatabase = bookDatabase
            )
            val pagingState = PagingState<Int, Book>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )
            val result = remoteMediator.load(loadType = LoadType.REFRESH, state = pagingState)
            assertTrue(result is MediatorResult.Success)
            assertTrue((result as MediatorResult.Success).endOfPaginationReached)
        }

    @Test
    fun refresh_load_return_error_result_when_Error_happened() =
        runBlocking {
            bookApi2.addException()
            val remoteMediator = com.coffee.bookapp.data.paging_source.BookRemoteMediator(
                bookApi = bookApi2,
                bookDatabase = bookDatabase
            )
            val pagingState = PagingState<Int, Book>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )
            val result = remoteMediator.load(loadType = LoadType.REFRESH, state = pagingState)
            assertTrue(result is MediatorResult.Error)
        }

}