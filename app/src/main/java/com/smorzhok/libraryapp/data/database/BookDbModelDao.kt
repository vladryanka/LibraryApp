package com.smorzhok.libraryapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.smorzhok.libraryapp.data.entities.BookDbModel

@Dao
interface BookDbModelDao {
    @Query("SELECT * FROM books")
    fun getBooks(): LiveData<List<BookDbModel>>

    @Query("SELECT EXISTS(SELECT 1 FROM books WHERE id = :id)")
    suspend fun isBookExists(id: Int): Boolean

    @Query("SELECT * FROM books WHERE id = :id")
    fun getBookById(id:Int): BookDbModel

    @Insert
    fun addBooks(book: BookDbModel)
}