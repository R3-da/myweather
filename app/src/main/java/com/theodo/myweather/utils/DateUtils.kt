package com.theodo.myweather.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    fun formatTime(time: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
        val outputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val date = inputFormat.parse(time)
        return outputFormat.format(date ?: Date())
    }
}