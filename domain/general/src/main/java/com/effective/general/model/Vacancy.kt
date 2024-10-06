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
    val salaryFull: String,
    val salaryMin: String,
    val schedules: List<String>,
)

val emptyVacancy: Vacancy
    get() = Vacancy(
        id = "",
        title = "",
        streat = "",
        house = "",
        town = "",
        appiledNumber = 0,
        companyName = "",
        desritpion = "",
        experiencePreview = "",
        experience = "",
        isFavorite = false,
        lookingNumber = 0,
        publishedDate = LocalDate.now(),
        questions = emptyList(),
        responsibilities = "",
        salaryFull = "",
        salaryMin = "",
        schedules = emptyList(),
    )
