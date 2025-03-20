package com.smorzhok.libraryapp.data

import android.util.Log
import com.smorzhok.libraryapp.data.database.dao.BookDbModelDao
import com.smorzhok.libraryapp.data.entities.BookDbModel
import com.smorzhok.libraryapp.data.entities.BookDto
import com.smorzhok.libraryapp.data.remote.LibraryApi
import com.smorzhok.libraryapp.data.remote.NetworkResult
import com.smorzhok.libraryapp.data.remote.handleApi
import com.smorzhok.libraryapp.domain.Book
import com.smorzhok.libraryapp.domain.LibraryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LibraryRepositoryImpl(private val bookDbModelDao: BookDbModelDao) : LibraryRepository {
    private val mapper: BookMapper = BookMapper()
    override suspend fun addBookToFavorites(book: BookDbModel) {
        return withContext(Dispatchers.IO) {
                bookDbModelDao.addBooks(book)
                Log.d("Book", "Added to favorites: $book")
        }
    }

    override suspend fun deleteBookFromFavorites(book: BookDbModel) {
        return withContext(Dispatchers.IO) {
                bookDbModelDao.deleteBooksByTitle(book.title)
                Log.d("Book", "Removed from favorites: $book")
        }
    }

    override suspend fun getFavoriteBooks(): List<Book> {
        TODO("Not yet implemented")
    }

    override suspend fun getBookById(id: String): BookDbModel {
        return withContext(Dispatchers.IO) {
            val result = mapper.mapDtoToDbModel(LibraryApi.retrofitService.loadDetailInfo(id).body()!!)
            result
        }
    }

    suspend fun containsBook(id:Int): Boolean{
        return bookDbModelDao.containsBookById(id)
    }

    override suspend fun getVolumeIdByTitle(title: String): String {
        return withContext(Dispatchers.IO) {
           ""
        }
    }

    override suspend fun isBookExists(title:String): Boolean {
        return bookDbModelDao.isBookExists(title)
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
            }
        }

        Log.d("Doing", "Ответ " + bookList.toString())
        return Pair(errorType, books)
    }

}