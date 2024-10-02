package com.effective.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.effective.general.model.VacanciesAndFastFilters
import com.effective.general.repository.VacanciesRepository
import com.effective.home.ui.adapter_delegates.toUi
import com.effective.ui.common.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val vacanciesRepository: VacanciesRepository
) :
    ViewModel() {

    private val _vacanciesAndFastFilters =
        MutableStateFlow(VacanciesAndFastFilters(emptyList(), emptyList()))
    val vacanciesAndFastFilters = _vacanciesAndFastFilters.asStateFlow()

    private val _screenState =
        MutableStateFlow<ScreenState>(ScreenState.Empty)
    val screenState = _screenState.asStateFlow()


    init {
        loadVacanciesAndFastFilters()
    }


    private fun loadVacanciesAndFastFilters() = viewModelScope.launch {
        _screenState.value = ScreenState.Loading
        val result = vacanciesRepository.getVacanciesAndFastFiltes()
        result.onFailure {
            _screenState.value = ScreenState.Error
        }.onSuccess { vacanciesAndFastFilters ->
            _screenState.value = ScreenState.Ready
            _vacanciesAndFastFilters.value = vacanciesAndFastFilters
        }
    }

}