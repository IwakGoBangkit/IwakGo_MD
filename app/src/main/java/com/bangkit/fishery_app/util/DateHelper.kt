package com.bangkit.fishery_app.util


import java.text.SimpleDateFormat
import java.util.Locale

object DateHelper {
    fun getCurrentDate(date: String):String {
        val dateFormat = SimpleDateFormat("yyyy|MM|dd HH:mm", Locale.getDefault())
        return dateFormat.format(date)
    }
}