package com.ezbuy.domain.model.signinwithpassword

import com.ezbuy.common.utils.ClientID
import com.squareup.moshi.Json

data class LoginRequest(
    @Json(name = "username")
    val username: String,
    @Json(name = "password")
    val password: String,
    @Json(name = "client_id")
    val clientId: String = ClientID.EZBUY_CLIENT_ID,
)
