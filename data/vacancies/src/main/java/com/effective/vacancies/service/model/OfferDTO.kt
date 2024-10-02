package com.effective.vacancies.service.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class OfferDTO(
    @SerializedName("button")
    val button: ButtonDTO?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("title")
    val title: String?
)