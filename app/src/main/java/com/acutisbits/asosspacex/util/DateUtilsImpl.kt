package com.acutisbits.asosspacex.util

import com.acutisbits.asosspacex.core.UNKNOWN_STRING
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

private const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
private const val CALENDAR_DATE_FORMAT = "yyyy/dd/MM"
private const val DAY_TIME_FORMAT = "hh:mm:ss"

class DateUtilsImpl : DateUtils {

    private val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
    private val calendarDateFormat = SimpleDateFormat(CALENDAR_DATE_FORMAT, Locale.US)
    private val dayTimeFormat = SimpleDateFormat(DAY_TIME_FORMAT, Locale.US)

    override fun getDateDifferenceFromTodayInDays(date: String): String {
        val difference = abs(calculateDifference(date))
        return (difference / (24 * 60 * 60 * 1000)).toString()
    }

    override fun getFormattedDate(date: String): String =
        try {
            calendarDateFormat.format(dateFormat.parse(date) ?: "")
        } catch (exception: ParseException) {
            UNKNOWN_STRING
        }

    override fun getFormattedTime(date: String): String =
        try {
            dayTimeFormat.format(dateFormat.parse(date) ?: "")
        } catch (exception: ParseException) {
            UNKNOWN_STRING
        }

    override fun isDateInFuture(date: String): Boolean =
        calculateDifference(date) < 0

    private fun calculateDifference(date: String): Long {
        val currentTime = Calendar.getInstance()
        val otherDateTime = Calendar.getInstance()

        otherDateTime.time = dateFormat.parse(date) ?: Date()

        return currentTime.timeInMillis - otherDateTime.timeInMillis
    }
}
