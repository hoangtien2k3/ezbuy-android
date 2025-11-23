package com.ezbuy.data.usecase

import com.ezbuy.common.utils.Resource
import com.ezbuy.domain.model.home.HomeModel
import com.ezbuy.common.di.IoDispatcher
import com.ezbuy.data.mapper.HomeDomainModelMapper
import com.ezbuy.data.repository.HomeRepository
import com.ezbuy.domain.usecase.GetHomeUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetHomeUseCaseImpl @Inject constructor(
    private val getHomeRepository: HomeRepository,
    private val homeMapper: HomeDomainModelMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : GetHomeUseCase {
    override suspend operator fun invoke(): Resource<HomeModel> {
        return withContext(dispatcher) {
            when (val resource = getHomeRepository.getHome()) {
                is Resource.Success -> {
                    Resource.Success(homeMapper.mapToDomainModel(resource.data))
                }
                is Resource.Failure -> {
                    Resource.Failure(resource.error)
                }
            }
        }
    }
}
