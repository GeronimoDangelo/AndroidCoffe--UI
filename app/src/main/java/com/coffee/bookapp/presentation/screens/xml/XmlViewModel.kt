package com.coffee.bookapp.presentation.screens.xml

import androidx.lifecycle.ViewModel
import com.coffee.bookapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class XmlViewModel @Inject constructor(
    useCases: UseCases
) : ViewModel() {
    val getAllXmls = useCases.getAllXmlUseCase()
}