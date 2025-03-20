package com.smorzhok.libraryapp.domain

class LikeBookUseCase(private val repository: LibraryRepository) {
    suspend operator fun invoke(book: Book): Result<Unit> {
        return try {
            repository.addBookToFavorites(book)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}