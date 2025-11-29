package com.ezbuy.common

import com.ezbuy.common.dispatcher.AppCoroutineDispatchers
import com.ezbuy.common.dispatcher.AppCoroutineScope
import com.ezbuy.common.dispatcher.DefaultAppCoroutineDispatchers
import com.ezbuy.common.dispatcher.DefaultAppCoroutineScope
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface CoreModule {
    @Binds
    @Singleton
    fun appCoroutineDispatchers(impl: DefaultAppCoroutineDispatchers): AppCoroutineDispatchers

    @Binds
    @Singleton
    fun appCoroutineScope(impl: DefaultAppCoroutineScope): AppCoroutineScope
}
