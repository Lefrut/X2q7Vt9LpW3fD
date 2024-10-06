package com.effective.general.repository

import com.effective.general.model.VacanciesAndFastFilters
import com.effective.general.model.Vacancy

interface VacanciesRepository {

    suspend fun getVacanciesAndFastFiltes(): Result<VacanciesAndFastFilters>
    suspend fun getVacancyById(vacancyId: String): Result<Vacancy>

}