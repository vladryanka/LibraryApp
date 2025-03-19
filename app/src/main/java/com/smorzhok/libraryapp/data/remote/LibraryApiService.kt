package com.smorzhok.libraryapp.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.smorzhok.libraryapp.data.LibraryRepositoryImpl
import com.smorzhok.libraryapp.domain.LibraryRepository
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

private const val BASE_URL: String = "https://www.googleapis.com/"

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
    /*@GET("training/all")
    suspend fun loadTrainingsResponse(): TrainingResponse

    @GET("training/signed")
    suspend fun loadSignedTrainingResponse(
        @Header("Authorization") bearerToken: String,
    ): TrainingResponse

    @GET("person/all")
    suspend fun loadPersonsResponse(@Header("Authorization") bearerToken: String): PersonResponse

    @POST("auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest,
    ): Response<LoginResponse>

    @POST("auth/newTokens")
    suspend fun newTokens(@Header("Authorization") bearerToken: String): Response<Token>


    @POST("auth/newAccessToken")
    suspend fun newAccessToken(@Header("Authorization") bearerToken: String): Response<Token>

    @POST("auth/changePassword")
    suspend fun changePassword(
        @Header("Authorization") bearerToken: String,
        @Body training: Training,
    )

    @POST("auth/register")
    suspend fun register(
        @Body registerRequest: RegisterRequest,
    ): Response<RegistrationResponse>

    @POST("sign/add")
    suspend fun addSign(
        @Header("Authorization") bearerToken: String,
        @Query("trainingId") trainingId: String,
    ): Response<Void>

    @GET("admin/logs")
    fun getAdminLogs(): Response<String>

    @PUT("person/picture")
    suspend fun putImage(
        @Header("Authorization") bearerToken: String,
        @Body imageRequest: ImageRequest,
    ): Response<Void>

    @GET("person/picture")
    suspend fun getImage(
        @Header("Authorization") bearerToken: String,
    ): Response<ImageResponse>*/
}

object LibraryApi {
    val retrofitService: LibraryApiService by lazy {
        retrofit.create(LibraryApiService::class.java)
    }
}

object RepositoryProvider {

    private lateinit var repository: LibraryRepository

    fun initialize(
    ) {
        repository = LibraryRepositoryImpl()
    }

    fun getRepository(): LibraryRepository {
        if (!::repository.isInitialized) {
            throw IllegalStateException("Repository is not initialized. Call initialize() first.")
        }
        return repository
    }
}