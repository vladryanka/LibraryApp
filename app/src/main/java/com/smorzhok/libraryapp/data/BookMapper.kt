package com.smorzhok.libraryapp.data

import com.smorzhok.libraryapp.data.entities.BookDbModel
import com.smorzhok.libraryapp.data.entities.BookDto

class BookMapper {

    fun mapDtoToDbModel(model: BookDto): BookDbModel {
        return BookDbModel(
            title = model.volumeInfo.title ?: "",
            authors = model.volumeInfo.authors
                ?: listOf(),
            description = model.volumeInfo.description
                ?: "",
            imageLinks = model.volumeInfo.imageLinks,
            publishedDate = model.volumeInfo.publishedDate
                ?: ""
        )
    }
}