package com.effective.ui.common

sealed interface Ui {

    data object Loading: Ui
    data object Success: Ui
    data object Error: Ui
    data object Empty: Ui
}