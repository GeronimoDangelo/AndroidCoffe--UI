package com.example.bookapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bookapp.data.local.dao.*
import com.example.bookapp.domain.model.*

@Database(
    entities = [

        Book::class, BookRemoteKeys::class,
        Jetpack::class, JetpackRemoteKeys::class,
        XmlModel::class, XmlRemoteKeys::class],

    version = 1
)
@TypeConverters(DatabaseConverter::class)
abstract class BookDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao
    abstract fun bookRemoteKeysDao(): BookRemoteKeysDao

    abstract fun jetpackDao(): JetpackDao
    abstract fun jetpackRemoteKeysDao(): JetpackRemoteKeysDao

    abstract fun xmlDao(): XmlDao
    abstract fun xmlRemoteKeysDao(): XmlRemoteKeysDao


}