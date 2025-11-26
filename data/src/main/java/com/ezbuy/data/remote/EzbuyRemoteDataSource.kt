package com.ezbuy.data.remote

import com.ezbuy.common.dispatcher.AppCoroutineDispatchers
import com.ezbuy.common.utils.AppError.ApiException
import com.ezbuy.data.mapper.either.RemoteErrorMapper
import com.ezbuy.data.mapper.either.catchingApiException
import com.ezbuy.domain.model.DataResponse
import com.ezbuy.domain.model.signinwithpassword.KeycloakToken
import com.ezbuy.domain.model.signinwithpassword.LoginRequest
import com.github.michaelbull.result.Result
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface EzbuyRemoteDataSource {
    // login: auth-service
    suspend fun login(
        username: String,
        password: String,
    ): Result<DataResponse<KeycloakToken>, ApiException>
}

class EzbuyRemoteDataSourceImpl
    @Inject
    constructor(
        private val coroutineDispatchers: AppCoroutineDispatchers,
        private val remoteErrorMapper: RemoteErrorMapper,
        private val ezbuyAPI: EzbuyAPI,
    ) : EzbuyRemoteDataSource {
        override suspend fun login(
            username: String,
            password: String,
        ): Result<DataResponse<KeycloakToken>, ApiException> =
            withContext(
                coroutineDispatchers.io,
            ) {
                catchingApiException(remoteErrorMapper) {
                    ezbuyAPI.login(LoginRequest(username, password))
                }
            }
    }
