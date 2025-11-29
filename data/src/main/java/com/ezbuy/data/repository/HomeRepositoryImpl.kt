package com.ezbuy.data.repository

import com.ezbuy.common.utils.Resource
import com.ezbuy.data.extensions.handleAPICall
import com.ezbuy.data.mapper.HomeDomainModelMapper
import com.ezbuy.data.remote.datasource.DataSource
import com.ezbuy.domain.model.home.HomeModel
import com.ezbuy.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl
    @Inject
    constructor(
        private val dataSource: DataSource,
        private val homeMapper: HomeDomainModelMapper,
    ) : HomeRepository {
        override suspend fun getHome(): Resource<HomeModel> {
            return when (val resource = handleAPICall { dataSource.getHome() }) {
                is Resource.Success -> Resource.Success(homeMapper.mapToDomainModel(resource.data))
                is Resource.Failure -> Resource.Failure(resource.error)
                is Resource.Loading -> Resource.Loading
            }
        }
    }
