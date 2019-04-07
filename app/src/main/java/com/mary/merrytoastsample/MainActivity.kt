package com.mary.merrytoastsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mary.merrytoast.toast.Colors
import com.mary.merrytoast.toast.MerryToast
import com.mary.merrytoast.toast.Shapes

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        MerryToast.Builder(this).text("Big toast").textSize(50).build()

        MerryToast.Builder(this).color(Colors.BLUE).radius(10).text("Color toast").build()

        MerryToast.Builder(this).color(Colors.ORANGE).text("Image toast")
            .image(R.drawable.ic_launcher_background).imageSize(100).build()

        MerryToast.Builder(this).shape(Shapes.OVAL).color(Colors.VIOLET).text("Shape toast").build()

        MerryToast.Builder(this).textColor(Colors.VIOLET).color(Colors.WHITE).text("Colors toast").build()

        MerryToast.Builder(this).image(R.drawable.icon_batman).color(Colors.BLACK).text("Batman toast")
            .shape(Shapes.OVAL).fixedSize().build()

        MerryToast.info(this, "Info toast")

        MerryToast.warn(this, "Warning toast")

        MerryToast.success(this, "Success toast")
    }
}
