package com.effective.bottom_menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.effective.general.repository.VacanciesDatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BottomMenuViewModel @Inject constructor(
    private val vacanciesDatabaseRepository: VacanciesDatabaseRepository
) : ViewModel() {

    init {
        loadCountFavorites()
    }

    private val _countFavorites = MutableStateFlow(0)
    val countFavorites = _countFavorites.asStateFlow()

    private fun loadCountFavorites() = viewModelScope.launch {
        vacanciesDatabaseRepository.getVacancies().onSuccess { vacanciesFlow ->
            vacanciesFlow.collectLatest { vacancies ->
                _countFavorites.update { vacancies.size }
            }
        }
    }

}