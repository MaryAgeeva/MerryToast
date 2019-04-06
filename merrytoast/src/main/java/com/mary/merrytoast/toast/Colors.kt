package com.mary.merrytoast.toast

import android.graphics.Color

enum class Colors(private val color: String) {
    YELLOW("#33FFFF00"), ORANGE("#33FFA500"), RED("#33FF0000"),
    VIOLET("#33FF00FF"), BLUE("#330000FF"), LIGHT_BLUE("#3300FFFF"),
    GREEN("#33008000"), LIME("3300FF00"),
    BROWN("#338B4513"), WHITE("#33FFFFFF"), BLACK("#33000000");

    internal fun getValue(): Int =
        try {
            Color.parseColor(color)
        } catch (e: Exception) {
            0
        }
}