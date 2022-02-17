package com.nikmaram.list.util

import android.content.Context

fun Context.dip2px(dpValue: Float): Int {
    val scale = resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}
