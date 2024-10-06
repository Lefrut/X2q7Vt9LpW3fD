package com.effective.vacancies.mappers

import com.effective.general.model.FastFilter
import com.effective.general.model.Vacancy
import com.effective.vacancies.room.model.FavoriteVacancyEntity
import com.effective.vacancies.service.model.OfferDTO
import com.effective.vacancies.service.model.VacancyDTO
import java.time.LocalDate


inline fun VacancyDTO.toDomain(
    onIncorrect: () -> Nothing = {
        throw IllegalArgumentException("Incorrect Vacancy DTO")
    }
): Vacancy {
    return Vacancy(
        id = id ?: onIncorrect(),
        title = title ?: onIncorrect(),
        streat = address?.street ?: "",
        house = address?.house ?: "",
        town = address?.town ?: "",
        appiledNumber = appliedNumber ?: 1,
        companyName = company ?: onIncorrect(),
        desritpion = description ?: "",
        experiencePreview = experience?.previewText ?: onIncorrect(),
        experience = experience.text ?: onIncorrect(),
        isFavorite = isFavorite ?: false,
        lookingNumber = lookingNumber ?: 0,
        publishedDate = LocalDate.parse(publishedDate ?: onIncorrect()),
        questions = questions?.map { it ?: onIncorrect() } ?: onIncorrect(),
        responsibilities = responsibilities ?: "",
        salaryFull = salary?.full ?: "",
        salaryMin = salary?.short ?: "",
        schedules = schedules?.filterNotNull() ?: emptyList()
    )
}

fun OfferDTO.toDomain(
    onIncorrect: () -> Nothing = {
        throw IllegalArgumentException("Incorrect OfferDTO")
    }
): FastFilter {
    return FastFilter(
        id =  id ?: onIncorrect(),
        link = link ?: "",
        title = title ?: "",
        buttonText = button?.text ?: ""
    )
}

fun FavoriteVacancyEntity.toDomain(): Vacancy {
    return Vacancy(
        id = id,
        title = title,
        streat = "",
        house = "",
        town = city,
        appiledNumber = 1,
        companyName = companyName,
        desritpion = "",
        experiencePreview = expiriencePreview,
        experience = "",
        isFavorite = isFavorite,
        lookingNumber = lookingNumber,
        publishedDate = publishDate,
        questions = emptyList(),
        responsibilities = "",
        salaryFull = "",
        salaryMin = "",
        schedules = emptyList()
    )
}

fun Vacancy.toData(): FavoriteVacancyEntity {
    return FavoriteVacancyEntity(
        isFavorite = isFavorite,
        lookingNumber = lookingNumber,
        publishDate = publishedDate,
        id = id,
        expiriencePreview = experiencePreview,
        title = title,
        city = town,
        companyName = companyName
    )
}