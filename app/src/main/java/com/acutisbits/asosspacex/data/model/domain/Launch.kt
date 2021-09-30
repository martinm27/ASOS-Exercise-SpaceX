package com.acutisbits.asosspacex.data.model.domain

import java.sql.Date

data class Launch(
    val id: Int,
    val missionImageUrl: String,
    val missionName: String,
    val missionArticleUrl: String,
    val missionWikipediaUrl: String,
    val missionVideoUrl: String,
    val isUpcoming: Boolean,
    val launchDate: Date,
    val rocket: Rocket?,
    val isLaunchSuccessful: Boolean
)
