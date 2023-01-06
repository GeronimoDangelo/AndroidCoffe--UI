package com.example.bookapp.di

import android.content.Context
import com.example.bookapp.data.repository.DataStoreOperationsImpl
import com.example.bookapp.data.repository.Repository
import com.example.bookapp.domain.repository.DataStoreOperations
import com.example.bookapp.domain.use_cases.UseCases
import com.example.bookapp.domain.use_cases.get_all_books.GetAllBooksUseCase
import com.example.bookapp.domain.use_cases.get_selected_book.GetSelectedBookUseCase
import com.example.bookapp.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.example.bookapp.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.example.bookapp.domain.use_cases.search_books.SearchBooksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext context: Context
    ): DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository),
            getAllBooksUseCase = GetAllBooksUseCase(repository),
            searchBooksUseCase = SearchBooksUseCase(repository),
            getSelectedBookUseCase = GetSelectedBookUseCase(repository)
        )
    }

}