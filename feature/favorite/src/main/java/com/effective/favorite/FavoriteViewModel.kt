package com.effective.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.effective.general.model.Vacancy
import com.effective.general.repository.VacanciesDatabaseRepository
import com.effective.ui.recycler.adapters.VacancyItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val vacanciesDatabaseRepository: VacanciesDatabaseRepository
) : ViewModel() {


    private val _favoriteVacancies = MutableStateFlow(emptyList<Vacancy>())
    val favoriteVacancies = _favoriteVacancies.asStateFlow()

    init {
        loadVacancies()
    }

    private fun loadVacancies() = viewModelScope.launch {
        val vacansiesFlow = vacanciesDatabaseRepository.getVacancies().getOrNull() ?: return@launch
        vacansiesFlow.collectLatest { vacansies ->
            _favoriteVacancies.update { vacansies }
        }
    }

    fun changeVacancyFavorite(vacancyItem: VacancyItem) = viewModelScope.launch {
        vacanciesDatabaseRepository.deleteVacancy(vacancyItem.id)
    }


}