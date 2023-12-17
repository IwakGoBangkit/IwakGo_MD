package com.bangkit.fishery_app.util


import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

object DateHelper {

    fun formatIsoDate(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

        inputFormat.timeZone = TimeZone.getTimeZone("GMT")
        val date = try {
            inputFormat.parse(inputDate)
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }

        return if (date != null) {
            outputFormat.format(date)
        } else {
            inputDate
        }
    }
}
