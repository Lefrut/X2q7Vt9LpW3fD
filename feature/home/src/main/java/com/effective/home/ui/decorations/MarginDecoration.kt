package com.effective.home.ui.decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class BottomMarginDecoration(private val spaceHeight: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            bottom = spaceHeight
        }
    }
}


class RightMarginDecoration(private val spaceHeight: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            val itemCount = parent.adapter?.itemCount ?: 0
            if(parent.getChildAdapterPosition(view) != itemCount - 1){
                right = spaceHeight
            }
        }
    }
}

class HeadlineDecoration(private val spaceHeight: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val viewHolder = parent.getChildViewHolder(view)
        val itemType = viewHolder.itemViewType

        if(itemType == 1){
            with(outRect) {
                top = spaceHeight
            }
        }

    }
}

