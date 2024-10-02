package com.effective.vacancies.service

import com.effective.vacancies.service.model.VacanciesDataDTO
import retrofit2.Response
import retrofit2.http.GET

interface VacanciesService {

    @GET("uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download")
    suspend fun getVacanciesData(): Response<VacanciesDataDTO>

}