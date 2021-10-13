package rui.utils

import rui.todd.ToddApplication

fun Int.dp2px(): Int {
    val scale: Float = ToddApplication.getApplication().resources.displayMetrics.density //当前屏幕密度因子
    return (this * scale + 0.5f).toInt()
}

fun Float.dp2px(): Int {
    val scale: Float = ToddApplication.getApplication().resources.displayMetrics.density //当前屏幕密度因子
    return (this * scale + 0.5f).toInt()
}

fun Double.dp2px(): Int {
    val scale: Float = ToddApplication.getApplication().resources.displayMetrics.density //当前屏幕密度因子
    return (this * scale + 0.5f).toInt()
}

fun getScreenWidth(): Int {
    val displayMetrics = ToddApplication.getApplication().resources.displayMetrics//当前屏幕密度因子
    return displayMetrics.widthPixels
}

fun getScreenHeight(): Int {
    val displayMetrics = ToddApplication.getApplication().resources.displayMetrics//当前屏幕密度因子
    return displayMetrics.heightPixels
}