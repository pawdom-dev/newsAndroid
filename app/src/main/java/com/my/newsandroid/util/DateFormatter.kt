package com.my.newsandroid.util

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

object DateFormatter {
    private val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }
    private val outputFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())

    fun formatIsoDate(isoDate: String): String {
        return try {
            // Handle case where date might have offset or fractional seconds
            val cleanedDate = isoDate.take(19) 
            val date = inputFormat.parse(cleanedDate)
            if (date != null) {
                outputFormat.format(date)
            } else {
                isoDate
            }
        } catch (e: Exception) {
            isoDate
        }
    }
}
