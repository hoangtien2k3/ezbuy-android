package com.ezbuy.domain.model

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class ErrorResponse(
    @Json(name = "message")
    val message: String?,
    @Json(name = "status_code")
    val statusCode: Int?,
    @Json(name = "error_code")
    val errorCode: String?,
)
