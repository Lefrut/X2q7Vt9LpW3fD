package com.effective.home.ui.adapter_delegates

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import com.effective.general.model.Vacancy
import com.effective.home.databinding.VacancyItemBinding
import com.effective.home.ui.common.HomeItem
import com.effective.home.ui.getAttrColor
import com.effective.resources.R
import com.effective.utils.strings.stringProviderImpl
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import java.time.format.TextStyle
import java.util.Locale
import com.google.android.material.R as MaterialRes

fun Vacancy.toUi(context: Context): VacancyItem {
    val monthName = publishedDate.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
    val dayOfMonth = publishedDate.dayOfMonth

    return VacancyItem(
        id = id,
        lookingNumber = context.resources.getQuantityString(
            R.plurals.viewers,
            lookingNumber,
            lookingNumber
        ),
        title = title,
        city = town,
        companyName = companyName,
        expirienceText = experiencePreview,
        publishDate = context.getString(R.string.published, dayOfMonth, monthName),
        isFavorite = isFavorite
    )
}

data class VacancyItem(
    val id: String,
    val lookingNumber: String,
    val title: String,
    val city: String,
    val companyName: String,
    val expirienceText: String,
    val publishDate: String,
    val isFavorite: Boolean,
) : HomeItem

@SuppressLint("PrivateResource")
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

                if (item.isFavorite) {
                    vacancyFavorite.setImageResource(R.drawable.favorite_24)
                    vacancyFavorite.imageTintList = ColorStateList.valueOf(
                        context.getAttrColor(
                            MaterialRes.attr.colorPrimary
                        )
                    )
                } else {
                    vacancyFavorite.setImageResource(R.drawable.favorite_border_24)
                    vacancyFavorite.imageTintList = ColorStateList.valueOf(
                        context.getAttrColor(
                            MaterialRes.attr.colorOnContainer
                        )
                    )
                }
            }
        }
    }
}

val testVacancyList = listOf(
    VacancyItem(
        "",
        lookingNumber = "Сейчас просматривают 100 человек",
        title = "Android-разработчик",
        city = "Москва",
        companyName = "Техно Решения",
        expirienceText = "Опыт работы 3+ года",
        publishDate = "2024-09-25",
        isFavorite = true
    ),
    VacancyItem(
        "",
        lookingNumber = "Сейчас просматривает 1 человек",
        title = "Kotlin-инженер",
        city = "Санкт-Петербург",
        companyName = "Инновационные Лаборатории",
        expirienceText = "Опыт работы 2+ года",
        publishDate = "2024-09-20",
        isFavorite = false
    ),
    VacancyItem(
        "",
        lookingNumber = "Сейчас просматривают 12 человек",
        title = "Разработчик мобильных приложений",
        city = "Новосибирск",
        companyName = "AppTech",
        expirienceText = "Опыт работы 4+ года",
        publishDate = "2024-09-28",
        isFavorite = true
    )
)



