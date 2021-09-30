package com.acutisbits.asosspacex.data.model.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class APILinks(

    @SerialName("mission_patch_small")
    val missionImageUrl: String?,

    @SerialName("article_link")
    val articleLink: String?,

    @SerialName("wikipedia")
    val wikipediaLink: String?,

    @SerialName("video_link")
    val videoLink: String?,
)
