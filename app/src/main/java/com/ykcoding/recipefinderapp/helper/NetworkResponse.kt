package com.ykcoding.recipefinderapp.helper

import java.io.IOException

sealed class NetworkResponse<out T: Any> {
    data class Success<T: Any>(val body: T): NetworkResponse<T>()

    sealed class Error: NetworkResponse<Nothing>() {
        data class Api(val code: Int, val message: String): Error()
        data class Network(val error: IOException): Error()
        data class Unknown(val error: Throwable): Error()

        fun handleErrorMessage(): String {
            return when(this) {
                is Api -> "$code : $message"
                is Network -> "Please check the internet connection. Something went wrong"
                is Unknown -> "An Unknown error occurred"
            }
        }
    }
}