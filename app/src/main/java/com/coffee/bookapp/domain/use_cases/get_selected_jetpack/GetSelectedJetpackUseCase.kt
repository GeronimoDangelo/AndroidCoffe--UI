package com.coffee.bookapp.domain.use_cases.get_selected_jetpack

import com.coffee.bookapp.data.repository.Repository
import com.coffee.bookapp.domain.model.Jetpack

class GetSelectedJetpackUseCase(private val repository: Repository) {
    suspend operator fun invoke(jetId: Int): Jetpack {
        return repository.getSelectedJetpack(jetId = jetId )
    }
}