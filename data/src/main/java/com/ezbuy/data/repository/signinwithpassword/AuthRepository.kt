package com.ezbuy.data.repository.signinwithpassword

import com.ezbuy.common.utils.AppError
import com.ezbuy.domain.model.signinwithpassword.KeycloakToken
import com.github.michaelbull.result.Result

interface AuthRepository {
    suspend fun login(
        username: String,
        password: String,
    ): Result<KeycloakToken?, AppError>
}
