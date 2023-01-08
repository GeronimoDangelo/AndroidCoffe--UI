package com.example.bookapp.presentation.screens.xmldetails

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.domain.model.XmlModel
import com.example.bookapp.domain.use_cases.UseCases
import com.example.bookapp.util.Constants.DETAILS_XML_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class XmlDetailsViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _selectedXml: MutableStateFlow<XmlModel?> = MutableStateFlow(null)
    val selectedXml: StateFlow<XmlModel?> = _selectedXml

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val xmlId = savedStateHandle.get<Int>(DETAILS_XML_KEY)
            _selectedXml.value =
                xmlId?.let { useCases.getSelectXmlUseCase(xmlId = xmlId) }
        }
    }

    private val _uiEvents3 = MutableSharedFlow<UiEvents3>()
    val uiEvents3: SharedFlow<UiEvents3> = _uiEvents3.asSharedFlow()

    private val _colorsPalette3: MutableState<Map<String, String>> = mutableStateOf(mapOf())
    val colorsPalette3: State<Map<String, String>> = _colorsPalette3

    fun generateColorsPalette3() {
        viewModelScope.launch {
            _uiEvents3.emit(UiEvents3.GenerateColorsPalette)
        }
    }

    fun setColorsPalette3(colors: Map<String, String>) {
        _colorsPalette3.value = colors

    }

}


sealed class UiEvents3 {
    object GenerateColorsPalette : UiEvents3()
}
