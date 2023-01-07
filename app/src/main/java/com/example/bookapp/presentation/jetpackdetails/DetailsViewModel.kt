package com.example.bookapp.presentation.jetpackdetails

import android.util.Log
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
    private val usesCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _selectedBook: MutableStateFlow<Jetpack?> = MutableStateFlow(null)
    val selectedBook: StateFlow<Jetpack?> = _selectedBook


    init {
        viewModelScope.launch(Dispatchers.IO) {
            val jetId = savedStateHandle.get<Int>(DETAILS_JETPACK_KEY)
            _selectedBook.value = jetId?.let { usesCases.getSelectedJetpackUseCase(jetId = jetId) }
            _selectedBook.value?.name?.let { Log.d("Book", it) }
        }
    }

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent.asSharedFlow()


    private val _colorPalette: MutableState<Map<String,String>> = mutableStateOf(mapOf())
    val colorPalette: State<Map<String,String>> = _colorPalette


    fun generateColorPalette(){
        viewModelScope.launch {
            _uiEvent.emit(UiEvent.GenerateColorPalette)
        }
    }

    fun setColorPalette(colors: Map<String,String> ){
        _colorPalette.value = colors
    }


}

sealed class UiEvent {
    object GenerateColorPalette: UiEvent()
}