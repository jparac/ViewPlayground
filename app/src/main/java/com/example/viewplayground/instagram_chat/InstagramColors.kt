package com.example.viewplayground.instagram_chat

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View

object InstagramColors {
    val color1 = Color.valueOf(20/255f, 228/255f, 255/255f)
    val color2 = Color.valueOf(226/255f, 42/255f, 232/255f)
}

fun View.setGradient(startColor: Color, endColor: Color) {
    val gradientDrawable = GradientDrawable(
        GradientDrawable.Orientation.TOP_BOTTOM,
        intArrayOf(
            startColor.toArgb(),
            endColor.toArgb(),
        )
    )
    background = gradientDrawable
}

fun lerpGradient(start: Color, end: Color, percent: Float): Color {
    val reg = start.red() + percent * (end.red() - start.red())
    val green = start.green() + percent * (end.green() - start.green())
    val blue = start.blue() + percent * (end.blue() - start.blue())
    return Color.valueOf(reg, green, blue)
}