package com.smorzhok.libraryapp.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.smorzhok.libraryapp.data.entities.BookDbModel

@Dao
interface FavouriteBookDao {
    @Query("SELECT * FROM favourite_books ")
    fun getBooks(): LiveData<List<BookDbModel>>
    @Insert
    fun addBooks(book: BookDbModel)
    @Delete
    fun deleteBooks(book: BookDbModel)
}