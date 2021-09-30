package com.acutisbits.asosspacex.data.model.domain

data class Launch(
    val id: Int,
    val missionImageUrl: String,
    val missionName: String,
    val missionArticleUrl: String,
    val missionWikipediaUrl: String,
    val missionVideoUrl: String,
    val isUpcoming: Boolean,
    val launchDate: String,
    val rocket: Rocket?,
    val isLaunchSuccessful: Boolean
)
