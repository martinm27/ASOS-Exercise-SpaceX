package com.acutisbits.asosspacex.data.model.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class APICompanyInfo(
    val name: String?,
    val founder: String?,

    @SerialName("founded")
    val foundationYear: Int?,

    @SerialName("employees")
    val employeesNumber: Int?,

    @SerialName("launch_sites")
    val launchSites: Int?,

    val valuation: Long?
)
