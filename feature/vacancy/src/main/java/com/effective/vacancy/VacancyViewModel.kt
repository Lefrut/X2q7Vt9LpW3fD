package com.effective.vacancy

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.effective.general.model.emptyVacancy
import com.effective.general.repository.VacanciesDatabaseRepository
import com.effective.general.repository.VacanciesRepository
import com.effective.ui.common.Ui
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VacancyViewModel @Inject constructor(
    private val vacanciesRepository: VacanciesRepository,
    private val vacanciesDatabaseRepository: VacanciesDatabaseRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _vacancyState = MutableStateFlow(emptyVacancy)
    val vacancyState = _vacancyState.asStateFlow()

    private val _uiState = MutableStateFlow<Ui>(Ui.Loading)
    val uiState = _uiState.asStateFlow()


    init {
        loadVacancyById()
    }

    private fun loadVacancyById() = viewModelScope.launch {
        _uiState.update { Ui.Loading }
        val vacancyId = savedStateHandle.get<String>("vacancy_id") ?: return@launch
        val vacancyResult = vacanciesRepository.getVacancyById(vacancyId)
        vacancyResult.onSuccess { currentVacancy ->
            val favoriteVacanciesFlow =
                vacanciesDatabaseRepository.getVacancies().getOrNull() ?: return@launch

            val favoriteVacancies = favoriteVacanciesFlow.firstOrNull() ?: return@launch

            val isFavoriteVacancy =
                favoriteVacancies.firstOrNull { it.id == currentVacancy.id } != null


            _uiState.update { Ui.Success }
            _vacancyState.update {
                if (isFavoriteVacancy) currentVacancy.copy(isFavorite = true)
                else currentVacancy.copy(isFavorite = false)
            }

        }
    }

}