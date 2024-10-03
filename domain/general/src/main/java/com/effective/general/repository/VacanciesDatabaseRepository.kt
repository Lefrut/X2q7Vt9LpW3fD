package com.effective.general.repository

import com.effective.general.model.Vacancy
import kotlinx.coroutines.flow.Flow

interface VacanciesDatabaseRepository {

    suspend fun deleteVacancy(vacancyId: String): Result<Unit>

    suspend fun deleteVacancy(vacancy: Vacancy): Result<Unit>
    suspend fun addVacancy(vacancy: Vacancy): Result<Unit>
    suspend fun getVacancies(): Result<Flow<List<Vacancy>>>

}