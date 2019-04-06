package com.mary.merrytoast.toast

import android.app.Activity
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.mary.merrytoast.R
import com.mary.merrytoast.createDrawable
import com.mary.merrytoast.empty
import com.mary.merrytoast.getColors
import kotlinx.android.synthetic.main.layout_toast.*
import kotlinx.android.synthetic.main.layout_toast.view.*
import java.lang.ref.WeakReference

class MerryToast internal constructor(context: Activity?, color: Int, text: String, textSize: Int,
                                     radius: Int, imageRes: Int, imageDrawable: Drawable?,
                                     imageSize: Int, duration: Int) : Toast(context) {

    init {
        if(context != null) {
            val layout = LayoutInflater.from(context).inflate(R.layout.layout_toast, context.toast_root)
            when {
                color != 0 ->
                    try {
                        layout.toast_root.background = createDrawable(color, radius)
                    } catch (e: Exception) {
                        Log.e("$TAG Inflate", "Error creating toast layout: $e")
                    }
                radius > 0 ->
                    try {
                        layout.toast_root.background = createDrawable(Colors.BLACK.getValue(), radius)
                    } catch (e: Exception) {
                        Log.e("$TAG Inflate", "Error creating toast layout: $e")
                    }
            }
            layout.toast_text.apply {
                this.text = text
                if(textSize > 0) this.textSize = textSize.toFloat()
            }
            layout.toast_icon.apply {
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
                if(imageSize > 0) {
                    layoutParams.apply {
                        width = imageSize
                        height = imageSize
                    }
                }
            }
            this.duration = if (duration in 0..1) duration else Toast.LENGTH_SHORT
            this.view = layout
            show()
        }
    }

    class Builder(context: Activity) {
        private var weakActivity: WeakReference<Activity> = WeakReference(context)
        private var color: Int = 0
        private var text: String = String.empty()
        private var radius: Int = 0
        private var imageRes: Int = 0
        private var imageDrawable: Drawable? = null
        private var duration: Int = 0
        private var textSize: Int = 0
        private var imageSize: Int = 0

        fun color(color: Colors) : Builder {
            this.color = color.getValue()
            return this
        }

        fun color(color: Int) : Builder {
            this.color = color
            return this
        }

        fun text(text: String) : Builder {
            this.text = text
            return this
        }

        fun textSize(textSize: Int) : Builder {
            this.textSize = textSize
            return this
        }

        fun radius(radius: Int) : Builder {
            this.radius = radius
            return this
        }

        fun image(image: Int) : Builder {
            this.imageRes = image
            return this
        }

        fun image(image: Drawable) : Builder {
            this.imageDrawable = image
            return this
        }

        fun imageSize(imageSize: Int) : Builder {
            this.imageSize = imageSize
            return this
        }

        fun duration(duration: Int) : Builder {
            this.duration = duration
            return this
        }

        fun shape(shape: Shapes) : Builder {
            this.radius = shape.radius
            return this
        }

        fun build() : MerryToast = MerryToast(weakActivity.get(), color, text, textSize, radius,
            imageRes, imageDrawable, imageSize, duration)
    }

    companion object {
        private const val TAG = "MerryToast"

        @JvmStatic
        fun info(context: Activity?, text: String, duration: Int) =
            MerryToast(context, color = context?.getColors(R.color.blue)?: 0, text = text, textSize = 0,
                radius = context?.resources?.getDimension(R.dimen.corner_radius_large)?.toInt()?: 0,
                imageRes = R.drawable.icon_info, imageDrawable = null, imageSize = 0, duration = duration)

        @JvmStatic
        fun info(context: Activity?, text: String) =
            MerryToast(context, color = context?.getColors(R.color.blue)?: 0, text = text, textSize = 0,
                radius = context?.resources?.getDimension(R.dimen.corner_radius_large)?.toInt()?: 0,
                imageRes = R.drawable.icon_info, imageDrawable = null, imageSize = 0, duration = 0)

        @JvmStatic
        fun warn(context: Activity?, text: String, duration: Int) =
            MerryToast(context, color = context?.getColors(R.color.red)?: 0, text = text, textSize = 0,
                radius = context?.resources?.getDimension(R.dimen.corner_radius_large)?.toInt()?: 0,
                imageRes = R.drawable.icon_warning, imageDrawable = null, imageSize = 0, duration = duration)

        @JvmStatic
        fun warn(context: Activity?, text: String) =
            MerryToast(context, color = context?.getColors(R.color.red)?: 0, text = text, textSize = 0,
                radius = context?.resources?.getDimension(R.dimen.corner_radius_large)?.toInt()?: 0,
                imageRes = R.drawable.icon_warning, imageDrawable = null, imageSize = 0, duration = 0)


        @JvmStatic
        fun success(context: Activity?, text: String, duration: Int) =
            MerryToast(context, color = context?.getColors(R.color.green)?: 0, text = text, textSize = 0,
                radius = context?.resources?.getDimension(R.dimen.corner_radius_large)?.toInt()?: 0,
                imageRes = R.drawable.icon_success, imageDrawable = null, imageSize = 0, duration = duration)

        @JvmStatic
        fun success(context: Activity?, text: String) =
            MerryToast(context, color = context?.getColors(R.color.green)?: 0, text = text, textSize = 0,
                radius = context?.resources?.getDimension(R.dimen.corner_radius_large)?.toInt()?: 0,
                imageRes = R.drawable.icon_success, imageDrawable = null, imageSize = 0, duration = 0)
    }
}