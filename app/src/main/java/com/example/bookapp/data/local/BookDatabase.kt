package com.example.bookapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bookapp.data.local.dao.BookDao
import com.example.bookapp.data.local.dao.BookRemoteKeysDao
import com.example.bookapp.data.local.dao.JetpackDao
import com.example.bookapp.data.local.dao.JetpackRemoteKeysDao
import com.example.bookapp.domain.model.Book
import com.example.bookapp.domain.model.BookRemoteKeys
import com.example.bookapp.domain.model.Jetpack
import com.example.bookapp.domain.model.JetpackRemoteKeys

@Database(entities = [Book::class, BookRemoteKeys::class,Jetpack::class,JetpackRemoteKeys::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class BookDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao
    abstract fun bookRemoteKeysDao(): BookRemoteKeysDao

    abstract fun jetpackDao(): JetpackDao
    abstract fun jetpackRemoteKeysDao(): JetpackRemoteKeysDao


}