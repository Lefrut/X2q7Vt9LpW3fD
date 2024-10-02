package com.effective.vacancies.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "favorite_vacancies")
data class FavoriteVacancyEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo("looking_number")
    val lookingNumber: Int,
    val title: String,
    val city: String,
    @ColumnInfo("company_name")
    val companyName: String,
    @ColumnInfo("expirience_text")
    val expiriencePreview: String,
    @ColumnInfo("published_date")
    val publishDate: LocalDate,
    @ColumnInfo("is_favorite")
    val isFavorite: Boolean,
)
