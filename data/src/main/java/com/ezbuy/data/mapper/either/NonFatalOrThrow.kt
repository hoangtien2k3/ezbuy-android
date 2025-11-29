package com.ezbuy.data.mapper.either

import kotlin.coroutines.cancellation.CancellationException

fun Throwable.nonFatalOrThrow(): Throwable = if (NonFatal(this)) this else throw this

@Suppress("FunctionName")
fun NonFatal(t: Throwable): Boolean =
    when (t) {
        is VirtualMachineError,
        is ThreadDeath,
        is InterruptedException,
        is LinkageError,
        is CancellationException,
        -> false
        else -> true
    }
