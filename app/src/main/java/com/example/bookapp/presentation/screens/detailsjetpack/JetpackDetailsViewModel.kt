package com.example.bookapp.presentation.screens.detailsjetpack

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.domain.model.Jetpack
import com.example.bookapp.domain.use_cases.UseCases
import com.example.bookapp.util.Constants.DETAILS_JETPACK_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JetpackDetailsViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _selectedJetpack: MutableStateFlow<Jetpack?> = MutableStateFlow(null)
    val selectedJetpack: StateFlow<Jetpack?> = _selectedJetpack

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val jetId = savedStateHandle.get<Int>(DETAILS_JETPACK_KEY)
            _selectedJetpack.value =
                jetId?.let { useCases.getSelectedJetpackUseCase(jetId = jetId) }
        }
    }

    private val _uiEvents = MutableSharedFlow<UiEvents>()
    val uiEvents: SharedFlow<UiEvents> = _uiEvents.asSharedFlow()

    private val _colorsPalette: MutableState<Map<String, String>> = mutableStateOf(mapOf())
    val colorsPalette: State<Map<String, String>> = _colorsPalette

    fun generateColorsPalette() {
        viewModelScope.launch {
            _uiEvents.emit(UiEvents.GenerateColorsPalette)
        }
    }

    fun setColorsPalette(colors: Map<String, String>) {
        _colorsPalette.value = colors

    }
}

sealed class UiEvents {
    object GenerateColorsPalette : UiEvents()
}
