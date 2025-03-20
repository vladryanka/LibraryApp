package com.smorzhok.libraryapp.domain

class UnlikeBookUseCase (private val repository: LibraryRepository) {
    suspend operator fun invoke(book: Book): Result<Unit> {
        return try {
            repository.deleteBookFromFavorites(book)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}