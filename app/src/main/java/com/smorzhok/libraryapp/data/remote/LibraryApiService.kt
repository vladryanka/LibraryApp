package com.smorzhok.libraryapp.data.remote

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.smorzhok.libraryapp.data.LibraryRepositoryImpl
import com.smorzhok.libraryapp.data.database.AppDatabase
import com.smorzhok.libraryapp.data.entities.BooksResponse
import com.smorzhok.libraryapp.domain.LibraryRepository
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

private const val BASE_URL: String = "https://www.googleapis.com/books/"

private val interceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}
val json = Json {
    ignoreUnknownKeys = true
}

private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(interceptor)
    .connectTimeout(60, TimeUnit.SECONDS)
    .readTimeout(60, TimeUnit.SECONDS)
    .writeTimeout(60, TimeUnit.SECONDS)
    .build()

private val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .build()


interface LibraryApiService {
    @GET("/books/v1/volumes")
    suspend fun loadBookByTitle(@Query("q") query: String): Response<BooksResponse>
}

object LibraryApi {
    val retrofitService: LibraryApiService by lazy {
        retrofit.create(LibraryApiService::class.java)
    }
}

object RepositoryProvider {

    private lateinit var repository: LibraryRepository

    fun initialize(context: Context) {
        val appDatabase = AppDatabase.getInstance(context)
        val bookDbModelDao = appDatabase.bookDbModelDao()
        repository = LibraryRepositoryImpl(bookDbModelDao)
    }

    fun getRepository(): LibraryRepository {
        if (!::repository.isInitialized) {
            throw IllegalStateException("Repository is not initialized. Call initialize() first.")
        }
        return repository
    }
}