package com.effective.home.ui.adapter_delegates

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.effective.home.databinding.FastFilterListItemBinding
import com.effective.home.ui.common.HomeItem
import com.effective.ui.recycler.decorations.RightMarginDecoration
import com.effective.ui.metrics.dpRoundToPx
import com.effective.ui.recycler.RecylerItem
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


@JvmInline
value class FastFilterListItem(
    val value: List<FastFilterItem>
) : HomeItem


fun fastFilterListAdapterDelegate(
    vararg adapterDelegates: AdapterDelegate<List<RecylerItem>>
): AdapterDelegate<List<RecylerItem>> {
    val fastFilterListAdapter = ListDelegationAdapter(*adapterDelegates)

    return adapterDelegateViewBinding<FastFilterListItem, RecylerItem, FastFilterListItemBinding>(
        { layoutInflater, parent ->
            FastFilterListItemBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        }
    ) {
        val horizontalRecyclerView = binding.horizontalRecyclerView
        horizontalRecyclerView.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        horizontalRecyclerView.adapter = fastFilterListAdapter
        horizontalRecyclerView.addItemDecoration(
            RightMarginDecoration(8.dpRoundToPx(context))
        )

        bind {
            fastFilterListAdapter.items = item.value
        }

    }
}