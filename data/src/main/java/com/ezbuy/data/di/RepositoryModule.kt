package com.ezbuy.data.di

import com.ezbuy.data.mapper.either.LocalErrorMapper
import com.ezbuy.data.mapper.either.LocalErrorMapperImpl
import com.ezbuy.data.mapper.either.RemoteErrorMapper
import com.ezbuy.data.mapper.either.RemoteErrorMapperImpl
import com.ezbuy.data.remote.datasource.DataSource
import com.ezbuy.data.repository.DetailRepository
import com.ezbuy.data.repository.DetailRepositoryImpl
import com.ezbuy.data.repository.HomeRepository
import com.ezbuy.data.repository.HomeRepositoryImpl
import com.ezbuy.data.repository.ListRepository
import com.ezbuy.data.repository.ListRepositoryImpl
import com.ezbuy.data.repository.signinwithpassword.AuthRepository
import com.ezbuy.data.repository.signinwithpassword.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {
    @Binds
    @Singleton
    fun provideHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

    @Binds
    @Singleton
    fun provideDetailRepository(detailRepositoryImpl: DetailRepositoryImpl): DetailRepository

    @Binds
    @Singleton
    fun provideListRepository(listRepositoryImpl: ListRepositoryImpl): ListRepository

    // =========================================== AUTH REPOSITORY ===========================================//
    @Binds
    @Singleton
    fun authRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    // ------------------- Binds Utils -------------------
    @Binds
    @Singleton
    fun localErrorMapper(implLocal: LocalErrorMapperImpl): LocalErrorMapper

    @Binds
    @Singleton
    fun remoteErrorMapper(implRemote: RemoteErrorMapperImpl): RemoteErrorMapper
}
