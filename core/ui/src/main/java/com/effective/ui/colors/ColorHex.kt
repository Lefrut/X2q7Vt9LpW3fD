package com.effective.ui.colors

import androidx.annotation.ColorInt

fun @receiver:ColorInt Int.toHex(alpha: Float = 1f): String {
    val alphaInt = (alpha * 255).toInt().coerceIn(0, 255)
    val aplhaHex = String.format("%02X", alphaInt)
    val colorHex = String.format("%06X", 0xFFFFFF and this)
    return "#$aplhaHex$colorHex"
}