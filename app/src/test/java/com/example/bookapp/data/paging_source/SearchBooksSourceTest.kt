package com.example.bookapp.data.paging_source

import androidx.paging.PagingSource.*
import com.example.bookapp.data.remote.BookApi
import com.example.bookapp.data.remote.FakeBookApi
import com.example.bookapp.domain.model.Book
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class SearchBooksSourceTest {

    private lateinit var bookApi: BookApi
    private lateinit var books: List<Book>

    @Before
    fun setup() {
        bookApi = FakeBookApi()
        books = listOf(
            Book(
                id = 1,
                name = "dario",
                image = "",
                about = "going out of home in march 2023",
                rating = 2.0,
                level = "cool",
                timeToLearn = "fast",
                tags = listOf()
            ),
            Book(
                id = 2,
                name = "diego",
                image = "",
                about = "europa2023",
                rating = 2.0,
                level = "cool",
                timeToLearn = "fast",
                tags = listOf()
            ),
            Book(
                id = 3,
                name = "russia",
                image = "",
                about = "enjoying life",
                rating = 2.0,
                level = "cool",
                timeToLearn = "fast",
                tags = listOf()
            )
        )
    }


    @Test
    fun `Search api with existing book name, expected single book item, assert LoadResult_Page`() =
        runTest {
            val bookSource = SearchBooksSource(bookApi = bookApi, query = "DARIO")
            assertEquals<LoadResult<Int, Book>>(
                expected = LoadResult.Page(
                    data = listOf(books.first()),
                    prevKey = null,
                    nextKey = null,
                ),
                actual = bookSource.load(
                    LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )
        }

    @Test
    fun `Search api with existing book name, expected multiple book items, assert LoadResult_Page`() =
        runTest {
            val bookSource = SearchBooksSource(bookApi = bookApi, query = "d")
            assertEquals<LoadResult<Int, Book>>(
                expected = LoadResult.Page(
                    data = listOf(books.first(), books[1]),
                    prevKey = null,
                    nextKey = null,
                ),
                actual = bookSource.load(
                    LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )
        }

    @Test
    fun `Search api with empty book name, assert  empty book list and LoadResult_Page`() =
        runTest {
            val bookSource = SearchBooksSource(bookApi = bookApi, query = "")
            val loadResult = bookSource.load(
                LoadParams.Refresh(
                    key = null,
                    loadSize = 3,
                    placeholdersEnabled = false
                )
            )
            val result = bookApi.searchBooks(name = "").books

            assertTrue { result.isEmpty() }
            assertTrue { loadResult is LoadResult.Page }

        }

    @Test
    fun `Search api with non_existing book name, assert  empty book list and LoadResult_Page`() =
        runTest {
            val bookSource = SearchBooksSource(bookApi = bookApi, query = "unknown")
            val loadResult = bookSource.load(
                LoadParams.Refresh(
                    key = null,
                    loadSize = 3,
                    placeholdersEnabled = false
                )
            )
            val result = bookApi.searchBooks(name = "unknown").books

            assertTrue { result.isEmpty() }
            assertTrue { loadResult is LoadResult.Page }

        }


}