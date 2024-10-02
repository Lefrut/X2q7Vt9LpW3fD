package com.effective.vacancies.repository

import com.effective.general.model.Vacancy
import com.effective.general.repository.VacanciesDatabaseRepository
import com.effective.vacancies.mappers.toData
import com.effective.vacancies.mappers.toDomain
import com.effective.vacancies.room.dao.VacanciesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VacanciesDatabaseRepositoryImpl @Inject constructor(
    private val vacanciesDao: VacanciesDao
) : VacanciesDatabaseRepository {

    override suspend fun deleteVacancy(vacancy: Vacancy): Result<Unit> = kotlin.runCatching {
        vacanciesDao.deleteVacancy(vacancy.toData())
    }

    override suspend fun addVacancy(vacancy: Vacancy): Result<Unit> = kotlin.runCatching {
        vacanciesDao.insertVacancy(vacancy.toData())
    }

    override suspend fun getVacancies(): Result<Flow<List<Vacancy>>> = kotlin.runCatching {
        return@runCatching vacanciesDao.getAllVacancies().map { list ->
            list.map { favoriteVacancyEntity -> favoriteVacancyEntity.toDomain() }
        }
    }
}