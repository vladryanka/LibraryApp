package com.smorzhok.libraryapp.domain

import com.smorzhok.libraryapp.data.entities.BookDbModel

class GetBooksByTitleUseCase (private val repository: LibraryRepository) {
    suspend operator fun invoke(title: String): Pair<String?,List<BookDbModel>?>{
        return repository.getBooksByTitle(title)
    }
}