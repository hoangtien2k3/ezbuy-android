package com.ezbuy.common.extensions

import com.ezbuy.common.utils.AppError
import com.ezbuy.common.utils.Resource

inline fun <T : Any> Resource<T>.onSuccess(action: (value: T) -> Unit): Resource<T> {
    if (this is Resource.Success) action(this.data)
    return this
}

inline fun <T : Any> Resource<T>.onFailure(action: (error: Throwable) -> Unit): Resource<T> {
    if (this is Resource.Failure) action(AppError.UnknownException(error))
    return this
}
