package com.effective.vacancies.service.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class VacanciesDataDTO(
    @SerializedName("offers")
    val offers: List<OfferDTO?>?,
    @SerializedName("vacancies")
    val vacancies: List<VacancyDTO?>?
)