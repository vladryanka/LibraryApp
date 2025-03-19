package com.smorzhok.libraryapp.domain

import kotlinx.serialization.SerialName

data class Book(
    @SerialName("name")
    val name: String
)
