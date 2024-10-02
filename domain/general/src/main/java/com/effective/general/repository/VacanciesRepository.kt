package com.effective.general.repository

import com.effective.general.model.VacanciesAndFastFilters

interface VacanciesRepository {

    suspend fun getVacanciesAndFastFiltes(): Result<VacanciesAndFastFilters>

}