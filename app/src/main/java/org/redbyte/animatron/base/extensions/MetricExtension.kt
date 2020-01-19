package org.redbyte.animatron.base.extensions

import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.util.TypedValue.COMPLEX_UNIT_DIP
import android.util.TypedValue.applyDimension
import android.view.WindowManager

fun Int.dp(): Int = toFloat().dp()

fun Float.dp(): Int {
    val displayMetrics = Resources.getSystem().displayMetrics
    val dp = applyDimension(COMPLEX_UNIT_DIP, this, displayMetrics)
    return dp.toInt()
}

fun Context.getScreenWidth(): Int {
    val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display = wm.defaultDisplay
    val size = Point()
    display.getSize(size)
    return size.x
}

fun Context.getScreenHeight(): Int {
    val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display = wm.defaultDisplay
    val size = Point()
    display.getSize(size)
    return size.y
}