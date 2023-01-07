package com.example.bookapp.domain.use_cases.get_selected_jetpack

import com.example.bookapp.data.repository.Repository
import com.example.bookapp.domain.model.Jetpack

class GetSelectedJetpackUseCase(private val repository: Repository) {
    suspend operator fun invoke(jetId: Int): Jetpack {
        return repository.getSelectedJetpack(jetId = jetId )
    }
}