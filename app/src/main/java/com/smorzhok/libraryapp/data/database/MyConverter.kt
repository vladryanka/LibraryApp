package com.smorzhok.libraryapp.data.database

import androidx.room.TypeConverter
import com.smorzhok.libraryapp.data.entities.ImageLinks
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MyConverter {
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromImageLinks(value: ImageLinks?): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toImageLinks(value: String): ImageLinks? {
        return Json.decodeFromString(value)
    }
}