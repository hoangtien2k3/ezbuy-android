package com.ezbuy.presentation.home.auth.signinwithpassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezbuy.common.utils.AppError
import com.ezbuy.common.utils.Resource
import com.ezbuy.data.repository.signinwithpassword.AuthRepository
import com.ezbuy.domain.model.signinwithpassword.KeycloakToken
import com.github.michaelbull.result.fold
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
    @Inject
    constructor(private val authRepository: AuthRepository) : ViewModel() {
        private val _signInViewModel = MutableSharedFlow<Resource<KeycloakToken>>()
        val signInViewModel = _signInViewModel.asSharedFlow()

        fun loginEzbuy(
            username: String,
            password: String,
        ) = viewModelScope.launch {
            _signInViewModel.emit(Resource.Loading)
            val result =
                authRepository.login(username, password)
                    .fold(
                        success = { token ->
                            if (token != null) {
                                Resource.Success(token)
                            } else {
                                Resource.Failure(AppError.UnknownException(NullPointerException("Token is null")))
                            }
                        },
                        failure = { throwable -> Resource.Failure(AppError.UnknownException(throwable)) },
                    )
            _signInViewModel.emit(result)
        }
    }
