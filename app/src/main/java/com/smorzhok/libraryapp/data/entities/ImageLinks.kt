package com.smorzhok.libraryapp.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageLinks(
    @SerialName("thumbnail")
    val thumbnail: String? = null
)