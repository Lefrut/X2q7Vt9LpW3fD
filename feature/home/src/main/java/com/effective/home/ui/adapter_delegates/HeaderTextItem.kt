package com.effective.home.ui.adapter_delegates

import com.effective.home.databinding.HeaderItemBinding
import com.effective.home.ui.common.HomeItem
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

@JvmInline
value class HeaderTextItem(
    val value: String
) : HomeItem


fun headlineTextAdapterDelegate(): AdapterDelegate<List<HomeItem>> {
    return adapterDelegateViewBinding<HeaderTextItem, HomeItem, HeaderItemBinding>(
        { layoutInflater, parent ->
            HeaderItemBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        }
    ) {
        binding.apply {
            bind { header.text = item.value }
        }
    }

}