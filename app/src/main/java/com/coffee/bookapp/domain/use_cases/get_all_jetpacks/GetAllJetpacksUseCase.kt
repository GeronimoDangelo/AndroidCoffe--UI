package com.coffee.bookapp.domain.use_cases.get_all_jetpacks

import androidx.paging.PagingData
import com.coffee.bookapp.data.repository.Repository
import com.coffee.bookapp.domain.model.Jetpack
import kotlinx.coroutines.flow.Flow

class GetAllJetpacksUseCase(private val repository: Repository) {

    operator fun invoke (): Flow<PagingData<Jetpack>> {
        return repository.getAllJetpacks()
    }

}