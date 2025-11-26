package com.ezbuy.domain.model.signinwithpassword

import com.squareup.moshi.Json

data class KeycloakToken(
    @Json(name = "access_token")
    val accessToken: String,
    @Json(name = "expires_in")
    val expiresIn: Int,
    @Json(name = "refresh_expires_in")
    val refreshExpiresIn: Int,
    @Json(name = "refresh_token")
    val refreshToken: String,
    @Json(name = "token_type")
    val tokenType: String,
)
