package com.berlin.uhr.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berlin.uhr.domain.BerlinClockUseCase
import com.berlin.uhr.domain.model.BerlinClockUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: BerlinClockUseCase,
) : ViewModel() {

    private val _berlinClockState = MutableStateFlow(BerlinClockUiState())
    val berlinClockState: StateFlow<BerlinClockUiState> = _berlinClockState.asStateFlow()

    init {
        startConversion()
    }

    private fun startConversion() {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.initConversion().collect {
                _berlinClockState.emit(it)
            }
        }
    }
}
