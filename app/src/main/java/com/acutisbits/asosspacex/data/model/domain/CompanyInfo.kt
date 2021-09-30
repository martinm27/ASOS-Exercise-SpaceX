package com.acutisbits.asosspacex.data.model.domain

import com.acutisbits.asosspacex.core.UNKNOWN_STRING

data class CompanyInfo(
    val name: String,
    val founder: String,
    val year: String,
    val employeesNumber: Int,
    val launchSitesNumber: Int,
    val valuation: String
) {
    companion object {
        val EMPTY = CompanyInfo(UNKNOWN_STRING, UNKNOWN_STRING, UNKNOWN_STRING, 0, 0, UNKNOWN_STRING)
    }
}
