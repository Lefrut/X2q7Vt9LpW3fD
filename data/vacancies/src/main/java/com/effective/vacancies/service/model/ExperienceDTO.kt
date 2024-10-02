package com.effective.vacancies.service.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ExperienceDTO(
    @SerializedName("previewText")
    val previewText: String?,
    @SerializedName("text")
    val text: String?
)