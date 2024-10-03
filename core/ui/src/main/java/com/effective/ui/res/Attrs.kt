package com.effective.ui.res

import android.content.Context
import android.util.TypedValue

fun getAttrColor(
    context: Context,
    firstAttrId: Int,
    secondAttrId: Int
): Pair<Int, Int> {
    val typedValueFirst = TypedValue()
    context.theme.resolveAttribute(firstAttrId, typedValueFirst, true)
    val typedValueSecond = TypedValue()
    context.theme.resolveAttribute(secondAttrId, typedValueSecond, true)
    return typedValueFirst.data to typedValueSecond.data
}

fun Context.getAttrColor(
    attrId: Int,
): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(attrId, typedValue, true)
    return typedValue.data
}