package com.example.bookapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookapp.domain.model.Book

@Dao
interface BookDao {

    @Query("SELECT * FROM book_table ORDER BY id ASC")
    fun getAllBooks(): PagingSource<Int, Book>

    @Query("SELECT * FROM book_table WHERE id=:bookId")
    fun getSelectedBook(bookId: Int): Book

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBooks(books: List<Book>)

    @Query("DELETE FROM book_table")
    suspend fun deleteAllBooks()

}