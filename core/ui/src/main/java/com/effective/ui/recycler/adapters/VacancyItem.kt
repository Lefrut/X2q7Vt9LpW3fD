package com.effective.ui.recycler.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import com.effective.general.model.Vacancy
import com.effective.resources.R
import com.effective.ui.databinding.VacancyItemBinding
import com.effective.ui.recycler.RecylerItem
import com.effective.ui.res.getAttrColor
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
) : RecylerItem

@SuppressLint("PrivateResource")
fun vacancyAdapterDelegate(
    onButtonClick: (VacancyItem) -> Unit,
    onFavoriteChange: (VacancyItem) -> Unit,
    onCardClick: (VacancyItem) -> Unit
): AdapterDelegate<List<RecylerItem>> {
    return adapterDelegateViewBinding<VacancyItem, RecylerItem, VacancyItemBinding>(
        { layoutInflater, parent ->
            VacancyItemBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        }
    ) {
        binding.root.setOnClickListener {
            onCardClick(item)
        }

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


