package com.smorzhok.libraryapp.data.remote

import retrofit2.HttpException
import retrofit2.Response

sealed class NetworkResult<out T> {
    data class Success<out T : Any>(val code: Int, val data: T) : NetworkResult<T>()
    data class Error(val code: Int, val errorMsg: String?) : NetworkResult<Nothing>()
    data class Exception(val e: Throwable) : NetworkResult<Nothing>()
}

suspend fun <T : Any> handleApi(
    execute: suspend () -> Response<T>,
): NetworkResult<T> {
    return try {
        val response = execute()

        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                NetworkResult.Success(response.code(), body)
            } else {
                NetworkResult.Error(response.code(), "Response body is null")
            }
        } else {
            NetworkResult.Error(response.code(), "Unknown Error")
        }
    } catch (e: HttpException) {
        NetworkResult.Error(e.code(), e.message())
    } catch (e: Throwable) {
        NetworkResult.Exception(e)
    }
}