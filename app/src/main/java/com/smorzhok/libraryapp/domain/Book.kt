package com.smorzhok.libraryapp.domain

import com.smorzhok.libraryapp.data.entities.ImageLinks
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Book(
    @Contextual
    var id: UUID,
    val title: String? = null,
    val authors: List<String>? = null,
    val description: String? = null,
    val imageLinks: ImageLinks? = null,
    val publishedDate: String? = null
)