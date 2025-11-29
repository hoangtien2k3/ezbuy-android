package com.ezbuy.data.repository.signinwithpassword

import com.ezbuy.common.utils.AppError
import com.ezbuy.data.remote.EzbuyRemoteDataSource
import com.ezbuy.domain.model.signinwithpassword.KeycloakToken
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val ezbuyRemoteDataSource: EzbuyRemoteDataSource,
) : AuthRepository {
    override suspend fun login(
        username: String,
        password: String,
    ): Result<KeycloakToken?, AppError> =
        ezbuyRemoteDataSource.login(username, password).map { it.data?.toKeycloakEntity() }
}

fun KeycloakToken.toKeycloakEntity(): KeycloakToken =
    KeycloakToken(
        accessToken = accessToken,
        expiresIn = expiresIn,
        refreshExpiresIn = refreshExpiresIn,
        refreshToken = refreshToken,
        tokenType = tokenType,
    )
