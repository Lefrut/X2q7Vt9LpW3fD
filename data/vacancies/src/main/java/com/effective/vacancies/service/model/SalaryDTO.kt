package com.effective.vacancies.service.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class SalaryDTO(
    @SerializedName("full")
    val full: String?,
    @SerializedName("short")
    val short: String?
)