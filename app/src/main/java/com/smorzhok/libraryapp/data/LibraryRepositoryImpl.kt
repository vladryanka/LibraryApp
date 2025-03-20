package com.smorzhok.libraryapp.data

import android.util.Log
import com.smorzhok.libraryapp.data.database.BookDbModelDao
import com.smorzhok.libraryapp.data.entities.BookDbModel
import com.smorzhok.libraryapp.data.entities.BookDto
import com.smorzhok.libraryapp.data.remote.LibraryApi
import com.smorzhok.libraryapp.data.remote.NetworkResult
import com.smorzhok.libraryapp.data.remote.handleApi
import com.smorzhok.libraryapp.domain.Book
import com.smorzhok.libraryapp.domain.BookDetails
import com.smorzhok.libraryapp.domain.LibraryRepository
import com.smorzhok.libraryapp.domain.SortType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LibraryRepositoryImpl(private val bookDbModelDao: BookDbModelDao) : LibraryRepository {
    private val mapper: BookMapper = BookMapper()
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

    override suspend fun getBookById(id:Int): BookDbModel{
        return withContext(Dispatchers.IO) {
            val book = bookDbModelDao.getBookById(id)
            Log.d("Book", "In repo ${book} + $id")
            book
        }
    }
    override suspend fun getBookByTitle(title:String): Int{
        return withContext(Dispatchers.IO) {
            bookDbModelDao.getIdByTitle(title)
        }
    }

    suspend fun isBookExists(id: Int): Boolean {
        return bookDbModelDao.isBookExists(id)
    }

    override suspend fun getBooksByTitle(title: String): Pair<String?, List<BookDbModel>?> {
        val result = handleApi {
            LibraryApi.retrofitService.loadBookByTitle(title)
        }
        var errorType: String? = null
        var bookList: List<BookDto>? = null
        val books = mutableListOf<BookDbModel>()

        when (result) {
            is NetworkResult.Success -> {
                Log.d("Doing", "Ответ тут" + result.data.toString())
                bookList = result.data.items
            }
            is NetworkResult.Error -> {
                errorType = result.errorMsg
                Log.d("Doing", "searching failed $errorType")
            }
            is NetworkResult.Exception -> {
                Log.d("Exception", result.e.toString())
                errorType = "Неизвестная ошибка"
            }
        }

        if (bookList != null) {
            for (i in bookList) {
                val mappedBook = mapper.mapDtoToDbModel(i)
                books.add(mappedBook)

                withContext(Dispatchers.IO) {
                    bookDbModelDao.addBooks(mappedBook)
                }
            }
        }

        Log.d("Doing", "Ответ " + bookList.toString())
        return Pair(errorType, books)
    }

}