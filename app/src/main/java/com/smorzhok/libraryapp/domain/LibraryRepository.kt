package com.smorzhok.libraryapp.domain

import com.smorzhok.libraryapp.data.entities.BookDbModel

interface LibraryRepository{
    suspend fun addBookToFavorites(book: Book)
    suspend fun deleteBookFromFavorites(book: Book)

    suspend fun removeBookFromFavorites(book: Book)
    suspend fun getFavoriteBooks(): List<Book>
    suspend fun isBookFavorite(bookId: String): Boolean

    suspend fun searchBooksByAuthor(author: String): List<Book>
    suspend fun sortBooks(books: List<Book>, sortType: SortType): List<Book>

    suspend fun getBookDetails(bookId: String): BookDetails

    suspend fun getBooksByTitle(title:String): Pair<String?,List<BookDbModel>?>

    suspend fun getBookById(id:Int): BookDbModel

    suspend fun getBookByTitle(title:String): Int
}