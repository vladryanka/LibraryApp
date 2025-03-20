package com.smorzhok.libraryapp.domain

class ContainsBookByIDUseCase(private val repository: LibraryRepository) {
    suspend operator fun invoke(title:String): Boolean{
        return repository.isBookExists(title)
    }
}