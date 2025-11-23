package com.ezbuy.presentation.common.extension

fun String?.notNullOrEmpty(): Boolean {
    return this?.isNotEmpty() ?: false
}
