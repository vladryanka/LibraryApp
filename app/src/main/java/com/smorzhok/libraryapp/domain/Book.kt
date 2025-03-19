package com.smorzhok.libraryapp.domain

import kotlinx.serialization.SerialName

data class Book(
    @SerialName("title")
    val title: String,
    @SerialName("author")
    val author: String,
    @SerialName("uri")
    val imageUrl: String
)
