package com.mary.merrytoast

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.support.annotation.ColorInt
import android.support.v4.content.ContextCompat

internal fun createDrawable(@ColorInt color: Int, radius: Int) = GradientDrawable().apply {
    this.setColor(color)
    this.cornerRadius = radius.toFloat()
}

internal fun Context.getColors(res: Int) = ContextCompat.getColor(this, res)

internal fun String.Companion.empty() = ""