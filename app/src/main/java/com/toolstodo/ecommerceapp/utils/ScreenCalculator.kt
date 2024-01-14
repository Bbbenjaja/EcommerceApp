package com.toolstodo.ecommerceapp.utils

import android.content.Context

object ScreenCalculator {

    const val DP_COLUMNS = 200f
    fun calculateNoOfColumns(context: Context, columnWidthDp: Float): Int {
        val displayMetrics = context.resources.displayMetrics
        val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
        return (screenWidthDp / columnWidthDp + 0.5).toInt() // +0.5 para redondear hacia arriba
    }
}