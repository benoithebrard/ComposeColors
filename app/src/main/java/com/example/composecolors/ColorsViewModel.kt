package com.example.composecolors

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlin.random.Random

class ColorsViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _colorFlow = MutableStateFlow(0xFFFFFFFF)
    val colorFlow = _colorFlow.asStateFlow()

    val redFlow = colorFlow.map { color ->
        color and 0xFFFF0000
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0xFFFFFFFF)

    val blueFlow = colorFlow.map { color ->
        color and 0xFF0000FF
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0xFF0000FF)

    val combinedFlow = combine(colorFlow, redFlow, blueFlow) { color, red, blue ->
        (color and red) or blue
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0xFFFFFFFF)

    // save state across activity recreation
    val savedColorFlow = savedStateHandle.getStateFlow(SAVED_STATE_HANDLE_COLOR, 0xFFFFFFFF)

    // same as flow, but using Compose instead
    var composeColor by mutableStateOf(0xFFFFFFFF)
        private set

    fun pickRandomColor() {
        val color = Random.nextLong(0xFFFFFFFF)
        _colorFlow.value = color
        savedStateHandle[SAVED_STATE_HANDLE_COLOR] = color
        composeColor = color

    }

    companion object {
        private const val SAVED_STATE_HANDLE_COLOR = "color"
    }
}