package com.coffee.bookapp.di

import android.content.Context
import com.coffee.bookapp.data.repository.DataStoreOperationsImpl
import com.coffee.bookapp.data.repository.Repository
import com.coffee.bookapp.domain.repository.DataStoreOperations
import com.coffee.bookapp.domain.use_cases.UseCases
import com.coffee.bookapp.domain.use_cases.get_all_books.GetAllBooksUseCase
import com.coffee.bookapp.domain.use_cases.get_all_jetpacks.GetAllJetpacksUseCase
import com.coffee.bookapp.domain.use_cases.get_all_xml.GetAllXmlUseCase
import com.coffee.bookapp.domain.use_cases.get_selected_book.GetSelectedBookUseCase
import com.coffee.bookapp.domain.use_cases.get_selected_jetpack.GetSelectedJetpackUseCase
import com.coffee.bookapp.domain.use_cases.get_selected_xmls.GetSelectXmlUseCase
import com.coffee.bookapp.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.coffee.bookapp.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.coffee.bookapp.domain.use_cases.search_books.SearchBooksUseCase
import com.coffee.bookapp.domain.use_cases.search_jetpack.SearchJetpackUseCase
import com.coffee.bookapp.domain.use_cases.search_xml.SearchXmlUseCase
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
            getSelectedBookUseCase = GetSelectedBookUseCase(repository),
            getAllJetpacksUseCase = GetAllJetpacksUseCase(repository),
            searchJetpacksUseCase = SearchJetpackUseCase(repository),
            getSelectedJetpackUseCase = GetSelectedJetpackUseCase(repository),
            getAllXmlUseCase = GetAllXmlUseCase(repository),
            searchXmlUseCase = SearchXmlUseCase(repository),
            getSelectXmlUseCase = GetSelectXmlUseCase(repository)
        )
    }

}