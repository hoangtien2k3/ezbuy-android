package com.ezbuy.data.di

import com.ezbuy.data.remote.datasource.DataSource
import com.ezbuy.data.repository.DetailRepository
import com.ezbuy.data.repository.DetailRepositoryImpl
import com.ezbuy.data.repository.HomeRepository
import com.ezbuy.data.repository.HomeRepositoryImpl
import com.ezbuy.data.repository.ListRepository
import com.ezbuy.data.repository.ListRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideHomeRepository(dataSource: DataSource): HomeRepository =
        HomeRepositoryImpl(
            dataSource,
        )

    @Singleton
    @Provides
    fun provideDetailRepository(dataSource: DataSource): DetailRepository =
        DetailRepositoryImpl(
            dataSource,
        )

    @Singleton
    @Provides
    fun provideListRepository(dataSource: DataSource): ListRepository =
        ListRepositoryImpl(
            dataSource,
        )
}
