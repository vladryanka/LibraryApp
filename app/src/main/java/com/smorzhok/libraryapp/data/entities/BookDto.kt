package com.smorzhok.libraryapp.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookDto(
    @SerialName("volumeInfo")
    val volumeInfo: VolumeInfo
)