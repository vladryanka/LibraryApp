package com.smorzhok.libraryapp.data

import com.smorzhok.libraryapp.domain.Book
import com.smorzhok.libraryapp.domain.BookDetails
import com.smorzhok.libraryapp.domain.LibraryRepository
import com.smorzhok.libraryapp.domain.SortType

class LibraryRepositoryImpl: LibraryRepository {
    override suspend fun addBookToFavorites(book: Book) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteBookFromFavorites(book: Book) {
        TODO("Not yet implemented")
    }

    override suspend fun removeBookFromFavorites(book: Book) {
        TODO("Not yet implemented")
    }

    override suspend fun getFavoriteBooks(): List<Book> {
        TODO("Not yet implemented")
    }

    override suspend fun isBookFavorite(bookId: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun searchBooksByAuthor(author: String): List<Book> {
        TODO("Not yet implemented")
    }

    override suspend fun sortBooks(books: List<Book>, sortType: SortType): List<Book> {
        TODO("Not yet implemented")
    }

    override suspend fun getBookDetails(bookId: String): BookDetails {
        TODO("Not yet implemented")
    }

}