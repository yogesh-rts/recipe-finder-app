package com.ykcoding.recipefinderapp.helper

import java.io.IOException

sealed class NetworkResponse<out T: Any> {
    data class Success<T: Any>(val body: T): NetworkResponse<T>()

    sealed class Error: NetworkResponse<Nothing>() {
        data class Api(val code: Int): Error()
        data class Network(val error: IOException): Error()
        data class Unknown(val error: Throwable): Error()

        fun handleErrorMessage(): String {
            return when(this) {
                is Api ->
                    when (code) {
                        400 -> "Invalid request. Please try again."
                        401 -> "You are not authorized."
                        403 -> "Access denied."
                        404 -> "The resource was not found."
                        408 -> "The request timed out. Please check your connection."
                        429 -> "Too many requests. Slow down!"
                        500 -> "Something went wrong on our end."
                        else -> "Unexpected error occurred."
                    }
                is Network -> "Please check the internet connection. Something went wrong"
                is Unknown -> "An Unknown error occurred"
            }
        }
    }
}