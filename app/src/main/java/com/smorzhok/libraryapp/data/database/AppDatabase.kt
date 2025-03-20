package com.smorzhok.libraryapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.smorzhok.libraryapp.data.database.AppDatabase.Companion.DATABASE_VERSION
import com.smorzhok.libraryapp.data.entities.BookDbModel

@Database(
    entities = [BookDbModel::class],
    version = DATABASE_VERSION
)
@TypeConverters(MyConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookDbModelDao(): BookDbModelDao

    companion object {

        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "LibraryDatabase"

        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}