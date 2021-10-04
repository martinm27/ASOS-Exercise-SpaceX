package com.acutisbits.asosspacex.data.model.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class APILaunch(
    @SerialName("flight_number")
    val id: Int?,

    @SerialName("mission_name")
    val missionName: String?,

    @SerialName("launch_year")
    val launchYear: String?,

    @SerialName("upcoming")
    val isUpcoming: Boolean?,

    @SerialName("launch_date_utc")
    val launchDate: String?,

    val rocket: APIRocket?,

    val links: APILinks?,

    @SerialName("launch_success")
    val isLaunchSuccessful: Boolean?
)
