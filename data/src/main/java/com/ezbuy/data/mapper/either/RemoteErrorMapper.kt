package com.ezbuy.data.mapper.either

import com.ezbuy.common.utils.AppError.ApiException
import com.ezbuy.domain.model.ErrorResponse
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.coroutines.runSuspendCatching
import com.github.michaelbull.result.mapError
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

interface RemoteErrorMapper : (Throwable) -> ApiException

@OptIn(ExperimentalContracts::class)
internal suspend inline fun <T> (suspend () -> T).catchingApiException(
    remoteErrorMapper: RemoteErrorMapper,
): Result<T, ApiException> {
    contract {
        callsInPlace(this@catchingApiException, InvocationKind.EXACTLY_ONCE)
    }
    return runSuspendCatching { this() }.mapError(remoteErrorMapper)
}

@OptIn(ExperimentalContracts::class)
internal inline fun <T> catchingApiException(
    remoteErrorMapper: RemoteErrorMapper,
    block: () -> T,
): Result<T, ApiException> {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return runSuspendCatching(block).mapError(remoteErrorMapper)
}

@Suppress("NOTHING_TO_INLINE")
internal inline fun <T> Flow<T>.catchingApiException(
    remoteErrorMapper: RemoteErrorMapper,
): Flow<Result<T, ApiException>> =
    map<_, Result<T, ApiException>> { Ok(it) }.catch {
        emit(
            Err(
                remoteErrorMapper(it),
            ),
        )
    }

private data class ErrorResponseException(
    override val message: String?,
    override val cause: Throwable?,
    val statusCode: Int?,
    val errorCode: String?,
) : Exception(cause)

internal class RemoteErrorMapperImpl
    @Inject
    constructor(
        private val errorResponseJsonAdapter: JsonAdapter<ErrorResponse>,
    ) : RemoteErrorMapper {
        override fun invoke(t: Throwable) =
            runCatching {
                when (val throwable = t.nonFatalOrThrow()) {
                    is ApiException -> throwable
                    is IOException ->
                        when (throwable) {
                            is UnknownHostException, is SocketException -> ApiException.NetworkException(throwable)
                            is SocketTimeoutException -> ApiException.TimeoutException(throwable)
                            else -> ApiException.UnknownException(throwable)
                        }

                    is HttpException ->
                        throwable
                            .response()!!
                            .takeUnless { it.isSuccessful }!!
                            .errorBody()!!
                            .use(ResponseBody::string)
                            .let { throwable.mapResponseError(it) }

                    else -> ApiException.UnknownException(throwable)
                }
            }.getOrElse { ApiException.UnknownException(it.nonFatalOrThrow()) }

        @Throws(Throwable::class)
        private fun HttpException.mapResponseError(json: String): ApiException.ServerException {
            val errorResponse = errorResponseJsonAdapter.fromJson(json)!!
            return ApiException.ServerException(
                statusCode = errorResponse.statusCode,
                cause =
                    ErrorResponseException(
                        message = errorResponse.message,
                        cause = this,
                        statusCode = errorResponse.statusCode,
                        errorCode = errorResponse.errorCode,
                    ),
            )
        }
    }
