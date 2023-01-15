package com.coffee.bookapp.data.remote

import com.example.bookapp.domain.model.ApiResponse
import com.example.bookapp.domain.model.Book

class FakeBookApi : BookApi {


    override suspend fun getAllBooks(page: Int): ApiResponse {
        return ApiResponse(
            success = false
        )
    }

    private val books = listOf(
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


    override suspend fun searchBooks(name: String): ApiResponse {

        val searchedBooks = findBooks(name = name)
        return ApiResponse(
            success = true,
            message = "ok",
            books = searchedBooks
        )

    }


    private fun findBooks(name: String): List<Book> {
        val founded = mutableListOf<Book>()
        return if (name.isNotEmpty()) {
            books.forEach { book ->
                if (book.name.lowercase().contains(name.lowercase())) {
                    founded.add(book)
                }
            }
            founded
        } else {
            emptyList()
        }
    }


    //
    //
    //
    //


    //
    //
    //
    //


    ///
    ///
    ///

    override suspend fun getAllJetpacks(page: Int): ApiResponse {
        TODO("Not yet implemented")
    }

    override suspend fun searchJetpack(name: String): ApiResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getAllXmls(page: Int): ApiResponse {
        TODO("Not yet implemented")
    }

    override suspend fun searchXml(name: String): ApiResponse {
        TODO("Not yet implemented")
    }
}