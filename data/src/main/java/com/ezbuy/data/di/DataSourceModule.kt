package com.ezbuy.data.di

import com.ezbuy.data.remote.Api
import com.ezbuy.data.remote.datasource.DataSource
import com.ezbuy.data.remote.datasource.DataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Singleton
    @Provides
    fun provideRemoteDataSource(api: Api): DataSource = DataSourceImpl(api)
}