package com.example.bookapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bookapp.data.local.dao.BookDao
import com.example.bookapp.data.local.dao.BookRemoteKeysDao
import com.example.bookapp.domain.model.Book
import com.example.bookapp.domain.model.BookRemoteKeys

@Database(entities = [Book::class, BookRemoteKeys::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class BookDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao
    abstract fun bookRemoteKeysDao(): BookRemoteKeysDao

}