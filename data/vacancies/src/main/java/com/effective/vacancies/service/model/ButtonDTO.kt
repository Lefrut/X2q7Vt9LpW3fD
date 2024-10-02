package com.effective.vacancies.service.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ButtonDTO(
    @SerializedName("text")
    val text: String?
)