package com.effective.vacancies.service.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class AddressDTO(
    @SerializedName("house")
    val house: String?,
    @SerializedName("street")
    val street: String?,
    @SerializedName("town")
    val town: String?
)