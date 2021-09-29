package com.acutisbits.asosspacex.ui.main.model

import com.acutisbits.asosspacex.coreui.utils.ListItem

data class LaunchViewState(
    override val id: Int,
    val missionPatchImageUrl: String,
    val missionName: String,
    val missionDate: String,
    val missionTime: String,
    val rocketData: String,
    val daysKey: String,
    val daysValue: String,
    val isLaunchedSuccessfully: Boolean
) : ListItem(id)
