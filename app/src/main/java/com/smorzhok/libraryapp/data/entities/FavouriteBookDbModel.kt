package com.smorzhok.libraryapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "favourite_books")
data class FavouriteBookDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val authors: List<String>,
    val description: String,
    val imageLinks: ImageLinks?,
    val publishedDate: String
)
