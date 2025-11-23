package com.ezbuy.data.repository

import com.ezbuy.common.utils.Resource
import com.ezbuy.data.extensions.handleAPICall
import com.ezbuy.data.model.DetailResponse
import com.ezbuy.data.remote.datasource.DataSource
import javax.inject.Inject

class DetailRepositoryImpl
    @Inject
    constructor(
        private val dataSource: DataSource,
    ) : DetailRepository {
        override suspend fun getDetail(): Resource<DetailResponse> {
            return handleAPICall { dataSource.getDetail() }
        }
    }
