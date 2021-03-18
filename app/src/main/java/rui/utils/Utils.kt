package rui.utils

import rui.todd.ToddApplication

fun Int.dp2px(): Float {
    val scale: Float = ToddApplication.getApplication().resources.displayMetrics.density //当前屏幕密度因子
    return this * scale + 0.5f
}

fun getScreenWidth(): Int {
    val displayMetrics = ToddApplication.getApplication().resources.displayMetrics//当前屏幕密度因子
    return displayMetrics.widthPixels
}

fun getScreenHeight(): Int {
    val displayMetrics = ToddApplication.getApplication().resources.displayMetrics//当前屏幕密度因子
    return displayMetrics.heightPixels
}