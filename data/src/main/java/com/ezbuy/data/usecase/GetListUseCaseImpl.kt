package com.ezbuy.data.usecase

import com.ezbuy.common.di.IoDispatcher
import com.ezbuy.domain.model.list.ListModel
import com.ezbuy.common.utils.Resource
import com.ezbuy.data.mapper.ListDomainModelMapper
import com.ezbuy.data.repository.ListRepository
import com.ezbuy.domain.model.list.ListRequestModel
import com.ezbuy.domain.usecase.GetListUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetListUseCaseImpl @Inject constructor(
    private val getListRepository: ListRepository,
    private val getListMapper: ListDomainModelMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : GetListUseCase {
    override suspend operator fun invoke(params: ListRequestModel): Resource<ListModel> {

        val resource = if (params.page == PAGING_NUMBER_DEFAULT) {
            getListRepository.getListFirst()
        } else {
            getListRepository.getListSecond()
        }

        return withContext(dispatcher) {
            when (resource) {
                is Resource.Success -> {
                    Resource.Success(getListMapper.mapToDomainModel(resource.data))
                }

                is Resource.Failure -> {
                    Resource.Failure(resource.error)
                }
            }
        }
    }

    companion object {
        const val PAGING_NUMBER_DEFAULT = 1
    }
}
