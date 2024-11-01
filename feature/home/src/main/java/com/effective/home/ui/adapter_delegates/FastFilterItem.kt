package com.effective.home.ui.adapter_delegates

import android.content.res.ColorStateList
import android.view.View
import androidx.annotation.DrawableRes
import com.effective.general.model.FastFilter
import com.effective.home.databinding.FastFilterItemBinding
import com.effective.home.ui.common.HomeItem
import com.effective.home.ui.getFastFilterIcon
import com.effective.home.ui.getPairColorInt
import com.effective.home.ui.getResId
import com.effective.resources.R
import com.effective.ui.recycler.RecylerItem
import com.effective.utils.strings.StringProviderImpl
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


fun FastFilter.toUi(): FastFilterItem {
    return FastFilterItem(
        iconId = getFastFilterIcon(id).getResId() ,
        text = title,
        buttonText = buttonText ?: ""
    )
}

data class FastFilterItem(
    @DrawableRes
    val iconId: Int?,
    val text: String,
    val buttonText: String
) : HomeItem


fun fastFilterAdapterDelegate(): AdapterDelegate<List<RecylerItem>> {
    return adapterDelegateViewBinding<FastFilterItem, RecylerItem, FastFilterItemBinding>(
        { layoutInflater, parent ->
            FastFilterItemBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        }
    ) {

        binding.apply {
            bind {
                fastFilterText.text = item.text
                if (item.buttonText.isNotBlank()) {
                    fastFilterButton.visibility = View.VISIBLE
                    fastFilterButton.text = item.buttonText
                } else {
                    fastFilterButton.visibility = View.GONE
                }



                item.iconId?.let { iconId ->
                    fastFilterIcon.setImageResource(iconId)
                    fastFilterIcon.visibility = View.VISIBLE
                    val (iconColor, backgroundColor) = iconId.getPairColorInt(context)
                    fastFilterIcon.imageTintList = ColorStateList.valueOf(iconColor)
                    fastFilterIconBackground.backgroundTintList = ColorStateList.valueOf(backgroundColor)
                } ?: run {
                    fastFilterIcon.visibility = View.GONE
                    fastFilterIconBackground.visibility = View.GONE
                }

            }
        }
    }

}

val testFastFilterList = listOf(
    FastFilterItem(R.drawable.place_24, "Вакансии рядом с вами", ""),
    FastFilterItem(R.drawable.star_24, "Поднять резюме в поиске", "Поднять"),
    FastFilterItem(R.drawable.list_done_24, "Временная работа и подработка", ""),
    FastFilterItem(null, "Остальное", "")
)
