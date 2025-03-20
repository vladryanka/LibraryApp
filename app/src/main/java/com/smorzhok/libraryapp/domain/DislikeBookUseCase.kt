package com.smorzhok.libraryapp.domain

import com.smorzhok.libraryapp.data.entities.BookDbModel

class DislikeBookUseCase (private val repository: LibraryRepository) {
    suspend operator fun invoke(book: BookDbModel): Boolean {
        return try {
            repository.deleteBookFromFavorites(book)
             true
        } catch (e: Exception) {
             false
        }
    }
}