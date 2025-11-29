package com.ezbuy.data.di

import com.ezbuy.common.di.IoDispatcher
import com.ezbuy.data.mapper.HomeDomainModelMapper
import com.ezbuy.data.repository.HomeRepository
import com.ezbuy.data.usecase.GetHomeUseCaseImpl
import com.ezbuy.domain.usecase.GetHomeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Singleton
    @Provides
    fun provideHomeUseCase(
        getHomeRepository: HomeRepository,
        homeDomainModelMapper: HomeDomainModelMapper,
        @IoDispatcher dispatcher: CoroutineDispatcher,
    ): GetHomeUseCase =
        GetHomeUseCaseImpl(
            getHomeRepository,
            homeDomainModelMapper,
            dispatcher,
        )

}
