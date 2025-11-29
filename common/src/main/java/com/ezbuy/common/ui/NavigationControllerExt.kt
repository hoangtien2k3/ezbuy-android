package com.ezbuy.common.ui

import androidx.navigation.NavController
import timber.log.Timber

val NavController.isGraphInitialized: Boolean
    get() =
        try {
            graph
            true
        } catch (_: IllegalStateException) {
            false
        }

val NavController.isGraphNotInitialized: Boolean get() = !isGraphInitialized

inline fun NavController.safeNavigate(block: NavController.() -> Unit) {
    try {
        this.block()
    } catch (e: IllegalArgumentException) {
        Timber.e(e, "Handled navigation destination not found issue gracefully.")
    }
}
