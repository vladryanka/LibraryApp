package com.smorzhok.libraryapp.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BooksResponse(
    @SerialName("items")
    val items: List<BookDto>
)