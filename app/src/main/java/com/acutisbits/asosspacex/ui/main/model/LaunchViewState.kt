package com.acutisbits.asosspacex.ui.main.model

data class LaunchViewState(
    val id: Int,
    val missionPatchImageUrl: String,
    val missionName: String,
    val missionDate: String,
    val missionTime: String,
    val rocketName: String,
    val rocketType: String,
    val daysSuffix: String,
    val daysSinceFromNow: String,
    val isLaunchedSuccessfully: Boolean
)
