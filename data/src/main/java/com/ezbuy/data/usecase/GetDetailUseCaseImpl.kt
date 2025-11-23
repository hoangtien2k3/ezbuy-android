package com.ezbuy.data.usecase

import com.ezbuy.common.di.IoDispatcher
import com.ezbuy.domain.model.detail.DetailModel
import com.ezbuy.common.utils.Resource
import com.ezbuy.data.mapper.DetailDomainModelMapper
import com.ezbuy.data.repository.DetailRepository
import com.ezbuy.domain.usecase.GetDetailUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetDetailUseCaseImpl @Inject constructor(
    private val getDetailRepository: DetailRepository,
    private val detailMapper: DetailDomainModelMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : GetDetailUseCase {
    override suspend operator fun invoke(): Resource<DetailModel> {
        return withContext(dispatcher) {
            when (val resource = getDetailRepository.getDetail()) {
                is Resource.Success -> {
                    Resource.Success(detailMapper.mapToDomainModel(resource.data))
                }

                is Resource.Failure -> {
                    Resource.Failure(resource.error)
                }
            }
        }
    }
}