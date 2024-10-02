package com.effective.general.model

import java.time.LocalDate

data class Vacancy(
    val id: String,
    val title: String,
    val streat: String,
    val house: String,
    val town: String,
    val appiledNumber: Int,
    val companyName: String,
    val desritpion: String,
    val experiencePreview: String,
    val experience: String,
    val isFavorite: Boolean,
    val lookingNumber: Int,
    val publishedDate: LocalDate,
    val questions: List<String>,
    val responsibilities: String,
    val salaryMax: String,
    val salaryMin: String,
    val schedules: List<String>,
)