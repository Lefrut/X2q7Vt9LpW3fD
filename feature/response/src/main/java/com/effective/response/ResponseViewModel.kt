package com.effective.response

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResponseViewModel @Inject constructor() : ViewModel() {

    private val _isCenterState = MutableStateFlow(false)
    val isCenterState = _isCenterState.asStateFlow()

    fun setCenterTrue() = viewModelScope.launch {
        _isCenterState.update { true }
    }


}