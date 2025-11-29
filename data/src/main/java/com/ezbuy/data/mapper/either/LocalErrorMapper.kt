package com.ezbuy.data.mapper.either

import android.database.SQLException
import com.ezbuy.common.utils.AppError.LocalStorageException
import com.ezbuy.common.utils.AppError.LocalStorageException.DatabaseException
import com.ezbuy.common.utils.AppError.LocalStorageException.UnknownException
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.coroutines.runSuspendCatching
import com.github.michaelbull.result.mapError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

interface LocalErrorMapper : (Throwable) -> LocalStorageException

@OptIn(ExperimentalContracts::class)
internal suspend inline fun <T> (suspend () -> T).catchingLocalStorageException(
    localErrorMapper: LocalErrorMapper,
): Result<T, LocalStorageException> {
    contract {
        callsInPlace(this@catchingLocalStorageException, InvocationKind.EXACTLY_ONCE)
    }
    return runSuspendCatching { this() }.mapError(localErrorMapper)
}

@OptIn(ExperimentalContracts::class)
internal inline fun <T> catchingLocalStorageException(
    localErrorMapper: LocalErrorMapper,
    block: () -> T,
): Result<T, LocalStorageException> {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return runSuspendCatching(block).mapError(localErrorMapper)
}

@Suppress("NOTHING_TO_INLINE")
internal inline fun <T> Flow<T>.catchingLocalStorageException(
    localErrorMapper: LocalErrorMapper,
): Flow<Result<T, LocalStorageException>> =
    map<_, Result<T, LocalStorageException>> { Ok(it) }.catch { emit(Err(localErrorMapper(it))) }

internal class LocalErrorMapperImpl
    @Inject
    constructor() : LocalErrorMapper {
        override fun invoke(t: Throwable): LocalStorageException =
            runCatching {
                when (val throwable = t.nonFatalOrThrow()) {
                    is LocalStorageException -> throwable
                    is IOException -> throwable.toLocalStorageException()
                    is SQLException -> DatabaseException(throwable)
                    else -> UnknownException(throwable)
                }
            }.getOrElse(Throwable::toLocalStorageException)
    }

private fun IOException.toLocalStorageException(): LocalStorageException =
    when (this) {
        is FileSystemException -> LocalStorageException.FileException(this)
        else -> UnknownException(this)
    }

private fun Throwable.toLocalStorageException(): UnknownException = UnknownException(nonFatalOrThrow())
