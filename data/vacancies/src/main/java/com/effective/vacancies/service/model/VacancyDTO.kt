package com.effective.vacancies.service.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class VacancyDTO(
    @SerializedName("address")
    val address: AddressDTO?,
    @SerializedName("appliedNumber")
    val appliedNumber: Int?,
    @SerializedName("company")
    val company: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("experience")
    val experience: ExperienceDTO?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("isFavorite")
    val isFavorite: Boolean?,
    @SerializedName("lookingNumber")
    val lookingNumber: Int?,
    @SerializedName("publishedDate")
    val publishedDate: String?,
    @SerializedName("questions")
    val questions: List<String?>?,
    @SerializedName("responsibilities")
    val responsibilities: String?,
    @SerializedName("salary")
    val salary: SalaryDTO?,
    @SerializedName("schedules")
    val schedules: List<String?>?,
    @SerializedName("title")
    val title: String?,
)