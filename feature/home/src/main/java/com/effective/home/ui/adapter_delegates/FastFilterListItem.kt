package com.effective.home.ui.adapter_delegates

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.effective.home.databinding.FastFilterListItemBinding
import com.effective.home.ui.common.HomeItem
import com.effective.home.ui.decorations.RightMarginDecoration
import com.effective.ui.metrics.dpRoundToPx
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


@JvmInline
value class FastFilterListItem(
    val value: List<FastFilterItem>
) : HomeItem


fun fastFilterListAdapterDelegate(
    vararg adapterDelegates: AdapterDelegate<List<HomeItem>>
): AdapterDelegate<List<HomeItem>> {
    val fastFilterListAdapter = ListDelegationAdapter(*adapterDelegates)

    return adapterDelegateViewBinding<FastFilterListItem, HomeItem, FastFilterListItemBinding>(
        { layoutInflater, parent ->
            FastFilterListItemBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        }
    ) {
        val horizontalRecyclerView = binding.horizontalRecyclerView
        bind {
            horizontalRecyclerView.layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            horizontalRecyclerView.adapter = fastFilterListAdapter
            fastFilterListAdapter.items = item.value

            horizontalRecyclerView.addItemDecoration(
                RightMarginDecoration(8.dpRoundToPx(context))
            )
        }

    }
}