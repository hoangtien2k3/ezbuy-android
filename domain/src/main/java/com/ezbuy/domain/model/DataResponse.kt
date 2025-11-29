package com.ezbuy.domain.model

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class DataResponse<T>(
    @Json(name = "error_code")
    val errorCode: String?,
    @Json(name = "message")
    val message: String?,
    @Json(name = "data")
    val data: T?,
)
