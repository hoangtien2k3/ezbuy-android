package com.ezbuy.data.di

import com.ezbuy.common.di.IoDispatcher
import com.ezbuy.data.mapper.DetailDomainModelMapper
import com.ezbuy.data.mapper.HomeDomainModelMapper
import com.ezbuy.data.mapper.ListDomainModelMapper
import com.ezbuy.data.repository.DetailRepository
import com.ezbuy.data.repository.HomeRepository
import com.ezbuy.data.repository.ListRepository
import com.ezbuy.data.usecase.GetDetailUseCaseImpl
import com.ezbuy.data.usecase.GetHomeUseCaseImpl
import com.ezbuy.data.usecase.GetListUseCaseImpl
import com.ezbuy.domain.usecase.GetDetailUseCase
import com.ezbuy.domain.usecase.GetHomeUseCase
import com.ezbuy.domain.usecase.GetListUseCase
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

    @Singleton
    @Provides
    fun provideDetailUseCase(
        getDetailRepository: DetailRepository,
        detailDomainModelMapper: DetailDomainModelMapper,
        @IoDispatcher dispatcher: CoroutineDispatcher,
    ): GetDetailUseCase =
        GetDetailUseCaseImpl(
            getDetailRepository,
            detailDomainModelMapper,
            dispatcher,
        )

    @Singleton
    @Provides
    fun provideListUseCase(
        getListRepository: ListRepository,
        listDomainModelMapper: ListDomainModelMapper,
        @IoDispatcher dispatcher: CoroutineDispatcher,
    ): GetListUseCase =
        GetListUseCaseImpl(
            getListRepository,
            listDomainModelMapper,
            dispatcher,
        )
}
