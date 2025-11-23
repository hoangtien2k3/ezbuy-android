package com.ezbuy.data.repository

import com.ezbuy.common.utils.Resource
import com.ezbuy.data.extensions.handleAPICall
import com.ezbuy.data.model.HomeResponse
import com.ezbuy.data.remote.datasource.DataSource
import javax.inject.Inject

class HomeRepositoryImpl
    @Inject
    constructor(
        private val dataSource: DataSource,
    ) : HomeRepository {
        override suspend fun getHome(): Resource<HomeResponse> {
            return handleAPICall { dataSource.getHome() }
        }
    }
