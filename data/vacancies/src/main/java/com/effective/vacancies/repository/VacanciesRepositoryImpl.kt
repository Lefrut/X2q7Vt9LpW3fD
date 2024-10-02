package com.effective.vacancies.repository

import com.effective.general.model.VacanciesAndFastFilters
import com.effective.general.repository.VacanciesRepository
import com.effective.utils.collections.saveMapNotNull
import com.effective.utils.network.getServiceExceptionByCode
import com.effective.vacancies.mappers.toDomain
import com.effective.vacancies.service.VacanciesService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VacanciesRepositoryImpl @Inject constructor(
    private val vacanciesService: VacanciesService
) : VacanciesRepository {

    override suspend fun getVacanciesAndFastFiltes(): Result<VacanciesAndFastFilters> =
        kotlin.runCatching {
            val response = vacanciesService.getVacanciesData()
            val body = response.body()!!

            if (response.isSuccessful) {
                val vacancies = body.vacancies?.saveMapNotNull { it?.toDomain() } ?: emptyList()
                val fastFilters = body.offers?.saveMapNotNull { it?.toDomain() } ?: emptyList()

                return@runCatching VacanciesAndFastFilters(
                    vacancies = vacancies,
                    fastFilters = fastFilters
                )
            } else {
                throw getServiceExceptionByCode(response.code())
            }
        }


}