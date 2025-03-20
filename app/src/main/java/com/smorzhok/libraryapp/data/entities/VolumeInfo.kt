package com.smorzhok.libraryapp.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VolumeInfo(
    @SerialName("title")
    val title: String? = null,
    @SerialName("authors")
    val authors: List<String>? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("imageLinks")
    val imageLinks: ImageLinks? = null,
    @SerialName("publishedDate")
    val publishedDate: String? = null
)