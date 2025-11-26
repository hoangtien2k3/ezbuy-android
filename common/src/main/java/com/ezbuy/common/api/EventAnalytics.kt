package com.ezbuy.common.api

import android.app.Activity

interface EventAnalytics {
    // Common
    fun screen(
        activity: Activity,
        screenName: String,
        screenClass: String?,
    )
}
