package com.effective.home.ui.adapter_delegates

import com.effective.home.databinding.VacanciesHeaderItemBinding
import com.effective.home.ui.common.HomeItem
import com.effective.resources.R
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

data class VacanciesHeaderItem(
    val vacanciesCount: Int
) : HomeItem

fun vacanciesHeaderAdapterDelegate(): AdapterDelegate<List<HomeItem>> {
    return adapterDelegateViewBinding<VacanciesHeaderItem, HomeItem, VacanciesHeaderItemBinding>(
        { layoutInflater, parent ->
            VacanciesHeaderItemBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        }
    ) {
        binding.apply {
            bind {
                vacanciesHeaderText.text = context.resources.getQuantityString(
                    R.plurals.vacancies_count,
                    item.vacanciesCount,
                    item.vacanciesCount
                )
            }
        }
    }
}
