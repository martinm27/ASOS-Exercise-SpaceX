package com.acutisbits.asosspacex.data.model.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class APIRocket(

    @SerialName("rocket_id")
    val id: String?,

    @SerialName("rocket_name")
    val name: String?,

    @SerialName("rocket_type")
    val type: String?
)
