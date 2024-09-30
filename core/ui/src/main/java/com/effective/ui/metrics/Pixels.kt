package com.effective.ui.metrics

import android.content.Context
import android.util.TypedValue
import kotlin.math.roundToInt

fun<T: Number> T.dpToPx(context: Context): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        context.resources.displayMetrics
    )
}

fun<T: Number> T.dpRoundToPx(context: Context): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        context.resources.displayMetrics
    ).roundToInt()
}