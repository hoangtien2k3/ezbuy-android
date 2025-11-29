package com.ezbuy.data.remote

import com.ezbuy.data.model.HomeResponse
import com.ezbuy.domain.model.DataResponse
import com.ezbuy.domain.model.signinwithpassword.KeycloakToken
import com.ezbuy.domain.model.signinwithpassword.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EzbuyAPI {
    @GET("/basaransuleyman/suleyman-basaranoglu-json/main/json-home-page")
    suspend fun getHome(): Response<HomeResponse>

    // Add other API endpoints as needed
    // Ezbuy Auth
    @POST("/v1/auth/login")
    suspend fun login(
        @Body request: LoginRequest,
    ): DataResponse<KeycloakToken>
}
