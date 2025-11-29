package com.ezbuy.common.utils

sealed class Resource<out T : Any> {
    data object Loading : Resource<Nothing>()

    data class Success<out T : Any>(val data: T) : Resource<T>()

    data class Failure(val error: AppError.UnknownException) : Resource<Nothing>()
}
