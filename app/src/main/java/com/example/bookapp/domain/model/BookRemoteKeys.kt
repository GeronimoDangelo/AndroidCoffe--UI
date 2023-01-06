package com.example.bookapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bookapp.util.Constants.BOOK_REMOTE_KEYS_DATABASE_TABLE

@Entity(tableName = BOOK_REMOTE_KEYS_DATABASE_TABLE)
data class BookRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long?
)
