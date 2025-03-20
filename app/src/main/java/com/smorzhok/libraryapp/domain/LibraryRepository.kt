package com.smorzhok.libraryapp.domain

import com.smorzhok.libraryapp.data.entities.BookDbModel

interface LibraryRepository{
    suspend fun addBookToFavorites(book: BookDbModel)
    suspend fun deleteBookFromFavorites(book: BookDbModel)

    suspend fun getFavoriteBooks(): List<Book>

    suspend fun getBooksByTitle(title:String): Pair<String?,List<BookDbModel>?>

    suspend fun getBookById(id:String): BookDbModel

    suspend fun isBookExists(title: String): Boolean

    suspend fun getVolumeIdByTitle(title:String): String
}