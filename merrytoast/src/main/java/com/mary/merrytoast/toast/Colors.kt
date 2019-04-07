package com.mary.merrytoast.toast

import android.graphics.Color

enum class Colors(private val color: String) {
    YELLOW("#FFFF00"), ORANGE("#FFA500"), RED("#FF0000"),
    VIOLET("#FF00FF"), BLUE("#0000FF"), LIGHT_BLUE("#00FFFF"),
    GREEN("#008000"), LIME("#00FF00"),
    BROWN("#8B4513"), WHITE("#FFFFFF"), BLACK("#000000");

    internal fun getValue(): Int =
        try {
            Color.parseColor(color)
        } catch (e: Exception) {
            0
        }
}