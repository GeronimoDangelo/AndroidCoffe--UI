package com.example.bookapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookapp.domain.model.BookRemoteKeys

@Dao
interface BookRemoteKeysDao {

    @Query("SELECT * FROM book_remote_keys_table WHERE id = :bookId")
    suspend fun getRemoteKeys(bookId: Int): BookRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(bookRemoteKeys: List<BookRemoteKeys>)

    @Query("DELETE FROM book_remote_keys_table")
    suspend fun deleteAllRemoteKeys()

}