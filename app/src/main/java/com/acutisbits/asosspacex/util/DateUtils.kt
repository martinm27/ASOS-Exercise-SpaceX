package com.acutisbits.asosspacex.util

interface DateUtils {

    fun getDateDifferenceFromTodayInDays(date: String): String

    fun getFormattedDate(date: String): String

    fun getFormattedTime(date: String): String

    fun isDateInFuture(date: String): Boolean
}
