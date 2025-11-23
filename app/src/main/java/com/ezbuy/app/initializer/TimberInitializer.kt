package com.ezbuy.app.initializer

import android.content.Context
import androidx.startup.Initializer
import com.ezbuy.app.BuildConfig
import com.ezbuy.app.crashlytics.CrashlyticsLoggerTree
import timber.log.Timber

class TimberInitializer : Initializer<Unit> {
  override fun create(context: Context) {
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    } else {
      Timber.plant(CrashlyticsLoggerTree())
    }
  }

  override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}
