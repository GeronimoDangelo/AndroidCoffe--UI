package com.coffee.bookapp.di

import android.content.Context
import androidx.room.Room
import com.coffee.bookapp.data.local.BookDatabase
import com.coffee.bookapp.data.repository.LocalDataSourceImpl
import com.coffee.bookapp.domain.repository.LocalDataSource
import com.coffee.bookapp.util.Constants.BOOK_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): BookDatabase {
        return Room.databaseBuilder(
            context,
            BookDatabase::class.java,
            BOOK_DATABASE,
        ).build()
    }


    @Provides
    @Singleton
    fun provideLocalDataSource(database: BookDatabase): LocalDataSource{
        return LocalDataSourceImpl(
            bookDatabase = database,

        )
    }

}