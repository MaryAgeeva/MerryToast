package com.mary.merrytoast.toast

import android.app.Activity
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.mary.merrytoast.*
import kotlinx.android.synthetic.main.layout_toast.*
import kotlinx.android.synthetic.main.layout_toast.view.*
import java.lang.ref.WeakReference

class MerryToast private constructor(context: Activity?, color: Int, text: String, textSize: Int,
                                     textColor: Int, radius: Int, imageRes: Int, imageDrawable: Drawable?,
                                     imageSize: Int, duration: Int, fixedSize: Boolean) : Toast(context) {

    private constructor(context: Activity?, color: Int, text: String, radius: Int, imageRes: Int,
                        duration: Int) : this(context, color, text, textSize = 0, textColor = 0,
        radius = radius, imageRes = imageRes,
        imageDrawable = null, imageSize = 0,
        duration = duration, fixedSize = true)

    init {
        if(context != null) {
            with(LayoutInflater.from(context).inflate(R.layout.layout_toast, context.toast_root)) {
                when {
                    color != 0 ->
                        try {
                            toast_root.background = createDrawable(color, radius)
                        } catch (e: Exception) {
                            Log.e("$TAG Inflate", "Error creating toast layout: $e")
                        }
                    radius > 0 ->
                        try {
                            toast_root.background = createDrawable(Colors.BLACK.getValue(), radius)
                        } catch (e: Exception) {
                            Log.e("$TAG Inflate", "Error creating toast layout: $e")
                        }
                }
                toast_text.apply {
                    this.text = text
                    if (textSize > 0) this.textSize = textSize.toFloat()
                    if(textColor != 0) this.setTextColor(textColor)
                    if(fixedSize) layoutParams.apply {
                        width = context.getDimen(R.dimen.toast_fixed_width)
                    }
                }
                toast_icon.apply {
                    visibility = when {
                        imageRes > 0 -> {
                            setImageResource(imageRes)
                            View.VISIBLE
                        }
                        imageDrawable != null -> {
                            setImageDrawable(imageDrawable)
                            View.VISIBLE
                        }
                        else -> View.GONE
                    }
                    if (imageSize > 0) {
                        layoutParams.apply {
                            width = imageSize
                            height = imageSize
                        }
                    }
                }
                this@MerryToast.duration = if (duration in 0..1) duration else Toast.LENGTH_SHORT
                this@MerryToast.view = this
                show()
            }
        }
    }

    class Builder(context: Activity) {
        private var weakActivity: WeakReference<Activity> = WeakReference(context)
        private var color: Int = 0
        private var text: String = String.empty()
        private var textSize: Int = 0
        private var textColor: Int = 0
        private var radius: Int = context.resources.getDimension(R.dimen.corner_radius_large).toInt()
        private var imageRes: Int = 0
        private var imageDrawable: Drawable? = null
        private var duration: Int = 0
        private var hasFixedSize: Boolean = false

        private var imageSize: Int = 0

        fun color(color: Colors) : Builder = apply {
            this.color = color.getValue()
        }

        fun color(@ColorInt color: Int) : Builder = apply {
            this.color = color
        }

        fun text(text: String) : Builder  = apply {
            this.text = text
        }

        fun textSize(textSize: Int) : Builder = apply {
            this.textSize = textSize
        }

        fun textColor(color: Colors) : Builder = apply {
            this.textColor = color.getValue()
        }

        fun textColor(@ColorInt color: Int) : Builder = apply {
            this.textColor = color
        }

        fun radius(radius: Int) : Builder  = apply {
            this.radius = radius
        }

        fun image(@DrawableRes image: Int) : Builder = apply {
            this.imageRes = image
        }

        fun image(image: Drawable) : Builder = apply {
            this.imageDrawable = image
        }

        fun imageSize(imageSize: Int) : Builder = apply {
            this.imageSize = imageSize
        }

        fun duration(duration: Int) : Builder = apply {
            this.duration = duration
        }

        fun shape(shape: Shapes) : Builder = apply {
            this.radius = shape.radius
        }

        fun fixedSize() : Builder = apply {
            this.hasFixedSize = true
        }

        fun build() : MerryToast = MerryToast(weakActivity.get(), color, text, textSize, textColor, radius,
            imageRes, imageDrawable, imageSize, duration, hasFixedSize)
    }

    companion object {
        private const val TAG = "MerryToast"

        @JvmStatic
        fun info(context: Activity?, text: String, duration: Int) =
            MerryToast(context, color = context?.getColors(R.color.blue)?: 0, text = text,
                radius = context?.getDimen(R.dimen.corner_radius_large)?: 0, imageRes = R.drawable.icon_info, duration = duration)

        @JvmStatic
        fun info(context: Activity?, text: String) =
            MerryToast(context, color = context?.getColors(R.color.blue)?: 0, text = text,
                radius = context?.getDimen(R.dimen.corner_radius_large)?: 0, imageRes = R.drawable.icon_info, duration = 0)

        @JvmStatic
        fun warn(context: Activity?, text: String, duration: Int) =
            MerryToast(context, color = context?.getColors(R.color.red)?: 0, text = text,
                radius = context?.getDimen(R.dimen.corner_radius_large)?: 0, imageRes = R.drawable.icon_warning, duration = duration)

        @JvmStatic
        fun warn(context: Activity?, text: String) =
            MerryToast(context, color = context?.getColors(R.color.red)?: 0, text = text,
                radius = context?.getDimen(R.dimen.corner_radius_large)?: 0, imageRes = R.drawable.icon_warning, duration = 0)


        @JvmStatic
        fun success(context: Activity?, text: String, duration: Int) =
            MerryToast(context, color = context?.getColors(R.color.green)?: 0, text = text,
                radius = context?.getDimen(R.dimen.corner_radius_large)?: 0, imageRes = R.drawable.icon_success, duration = duration)

        @JvmStatic
        fun success(context: Activity?, text: String) =
            MerryToast(context, color = context?.getColors(R.color.green)?: 0, text = text,
                radius = context?.getDimen(R.dimen.corner_radius_large)?: 0, imageRes = R.drawable.icon_success, duration = 0)
    }
}