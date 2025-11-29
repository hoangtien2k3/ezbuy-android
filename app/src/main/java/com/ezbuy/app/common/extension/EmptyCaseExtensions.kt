package com.ezbuy.app.common.extension

fun String?.notNullOrEmpty(): Boolean {
    return this?.isNotEmpty() ?: false
}
