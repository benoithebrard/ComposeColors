package com.example.composecolors

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

class ColorsViewModel : ViewModel() {

    private val _colorFlow = MutableStateFlow(0xFFFFFFFF)
    val colorFlow = _colorFlow.asStateFlow()

    // same as flow, but using Compose instead
    var composeColor by mutableStateOf(0xFFFFFFFF)
        private set

    fun pickRandomColor() {
        val color = Random.nextLong(0xFFFFFFFF)
        _colorFlow.value = color
        composeColor = color

    }
}