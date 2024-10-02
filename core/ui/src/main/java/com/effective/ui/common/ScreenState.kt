package com.effective.ui.common

sealed interface ScreenState {

    data object Loading: ScreenState
    data object Ready: ScreenState
    data object Error: ScreenState
    data object Empty: ScreenState

}