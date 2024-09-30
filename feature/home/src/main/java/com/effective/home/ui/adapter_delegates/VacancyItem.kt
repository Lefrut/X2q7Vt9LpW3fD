package com.effective.home.ui.adapter_delegates

import com.effective.home.databinding.VacancyItemBinding
import com.effective.home.ui.common.HomeItem
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

data class VacancyItem(
    val lookingNumber: String,
    val title: String,
    val city: String,
    val companyName: String,
    val expirienceText: String,
    val publishDate: String,
    val isFavorite: Boolean,
) : HomeItem

fun vacancyAdapterDelegate(
    onButtonClick: (VacancyItem) -> Unit,
    onFavoriteChange: (VacancyItem) -> Unit
): AdapterDelegate<List<HomeItem>> {
    return adapterDelegateViewBinding<VacancyItem, HomeItem, VacancyItemBinding>(
        { layoutInflater, parent ->
            VacancyItemBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        }
    ) {

        binding.vacancyButton.setOnClickListener {
            onButtonClick(item)
        }
        binding.vacancyFavorite.setOnClickListener {
            onFavoriteChange(item)
        }

        binding.apply {
            bind {
                vacancyLooking.text = item.lookingNumber
                vacancyTitle.text = item.title
                vacancyCity.text = item.city
                vacancyCompany.text = item.companyName
                vacancyExpirience.text = item.expirienceText
                vacancyDate.text = item.publishDate
            }
        }
    }
}

val testVacancyList = listOf(
    VacancyItem(
        lookingNumber = "Сейчас просматривают 100 человек",
        title = "Android-разработчик",
        city = "Москва",
        companyName = "Техно Решения",
        expirienceText = "Опыт работы 3+ года",
        publishDate = "2024-09-25",
        isFavorite = true
    ),
    VacancyItem(
        lookingNumber = "Сейчас просматривает 1 человек",
        title = "Kotlin-инженер",
        city = "Санкт-Петербург",
        companyName = "Инновационные Лаборатории",
        expirienceText = "Опыт работы 2+ года",
        publishDate = "2024-09-20",
        isFavorite = false
    ),
    VacancyItem(
        lookingNumber = "Сейчас просматривают 12 человек",
        title = "Разработчик мобильных приложений",
        city = "Новосибирск",
        companyName = "AppTech",
        expirienceText = "Опыт работы 4+ года",
        publishDate = "2024-09-28",
        isFavorite = true
    )
)



