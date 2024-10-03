package com.effective.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.effective.general.model.VacanciesAndFastFilters
import com.effective.general.repository.VacanciesDatabaseRepository
import com.effective.general.repository.VacanciesRepository
import com.effective.home.model.HomeUiState
import com.effective.ui.recycler.adapters.VacancyItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val vacanciesRepository: VacanciesRepository,
    private val vacanciesDatabaseRepository: VacanciesDatabaseRepository
) :
    ViewModel() {

    private val _vacanciesAndFastFilters =
        MutableStateFlow(VacanciesAndFastFilters(emptyList(), emptyList()))
    val vacanciesAndFastFilters = _vacanciesAndFastFilters.asStateFlow()

    private val _screenState = MutableStateFlow(HomeUiState.None)
    val screenState = _screenState.asStateFlow()


    init {
        loadData()
    }

    private fun loadData() = viewModelScope.launch {
        loadVacanciesAndFastFilters().join()
        listenFavoriteVacancies()
    }

    private fun listenFavoriteVacancies() = viewModelScope.launch {
        vacanciesDatabaseRepository.getVacancies().onSuccess { flow ->
            flow.collectLatest { vacancies ->
                val vacanciesIds = vacancies.map { vacancy -> vacancy.id }
                _vacanciesAndFastFilters.update { value ->
                    value.copy(
                        vacancies = value.vacancies.map { vacancy ->
                            if (vacanciesIds.contains(vacancy.id)) vacancy.copy(isFavorite = true)
                            else vacancy.copy(isFavorite = false)
                        }
                    )
                }
            }
        }
    }


    private fun loadVacanciesAndFastFilters() = viewModelScope.launch {
        _screenState.value = HomeUiState.Loading
        val result = vacanciesRepository.getVacanciesAndFastFiltes()
        result.onFailure {

        }.onSuccess { vacanciesAndFastFilters ->
            _screenState.value = HomeUiState.Home
            _vacanciesAndFastFilters.value = vacanciesAndFastFilters
        }
    }

    fun goToVacancies() = viewModelScope.launch {
        _screenState.value = HomeUiState.Vacancies
    }

    fun goToHome() {
        _screenState.value = HomeUiState.Home

    }

    fun changeVacancyFavorite(vacancyItem: VacancyItem) = viewModelScope.launch {
        val vacancy =
            vacanciesAndFastFilters.value.vacancies.firstOrNull { it.id == vacancyItem.id }
                ?: return@launch

        if (vacancy.isFavorite) {
            vacanciesDatabaseRepository.deleteVacancy(
                vacancy.copy(isFavorite = true)
            )
        } else {
            vacanciesDatabaseRepository.addVacancy(
                vacancy.copy(isFavorite = true)
            )
        }
    }

}