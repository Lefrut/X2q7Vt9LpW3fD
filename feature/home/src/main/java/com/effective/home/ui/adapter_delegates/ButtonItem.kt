package com.effective.home.ui.adapter_delegates

import com.effective.home.databinding.ButtonItemBinding
import com.effective.home.databinding.HeaderItemBinding
import com.effective.home.ui.common.HomeItem
import com.effective.ui.recycler.RecylerItem
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

data class ButtonItem(
    val text: String
): HomeItem


fun buttonAdapterDelegate(onClick: () -> Unit): AdapterDelegate<List<RecylerItem>> {
    return adapterDelegateViewBinding<ButtonItem, RecylerItem, ButtonItemBinding>(
        { layoutInflater, parent ->
            ButtonItemBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        }
    ) {
        binding.apply {
            bind {
                button.text = item.text

            }
        }.apply {
            button.setOnClickListener {
                onClick()
            }
        }
    }
}
