package com.coffee.bookapp.domain.use_cases.search_jetpack

import androidx.paging.PagingData
import com.coffee.bookapp.data.repository.Repository
import com.coffee.bookapp.domain.model.Jetpack
import kotlinx.coroutines.flow.Flow

class SearchJetpackUseCase(private val repository: Repository) {

    operator fun invoke(query:String) : Flow<PagingData<Jetpack>> {
        return repository.searchJetpack(query = query)
    }
}