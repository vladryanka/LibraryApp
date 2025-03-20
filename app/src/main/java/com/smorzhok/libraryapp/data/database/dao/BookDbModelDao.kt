package com.smorzhok.libraryapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.smorzhok.libraryapp.data.entities.BookDbModel

@Dao
interface BookDbModelDao {

    @Query("SELECT * FROM books")
    suspend fun getFavoriteBooks(): List<BookDbModel>

    @Query("SELECT EXISTS(SELECT 1 FROM books WHERE title = :title)")
    suspend fun isBookExists(title: String): Boolean

    @Query("SELECT * FROM books WHERE id = :id")
    fun getBookById(id: Int): BookDbModel

    @Query("SELECT * FROM books WHERE title = :title")
    fun getVolumeIdByTitle(title: String): BookDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBooks(book: BookDbModel)

    @Query("DELETE FROM books WHERE title = :title")
    suspend fun deleteBooksByTitle(title: String)

    @Update
    suspend fun updateBook(book: BookDbModel)

    @Query("SELECT EXISTS(SELECT 1 FROM books WHERE id = :id)")
    suspend fun containsBookById(id: Int): Boolean
}