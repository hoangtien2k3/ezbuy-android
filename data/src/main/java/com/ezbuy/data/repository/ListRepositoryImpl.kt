package com.ezbuy.data.repository

import com.ezbuy.common.utils.Resource
import com.ezbuy.data.extensions.handleAPICall
import com.ezbuy.data.model.ListResponse
import com.ezbuy.data.remote.datasource.DataSource
import javax.inject.Inject

class ListRepositoryImpl
    @Inject
    constructor(
        private val dataSource: DataSource,
    ) : ListRepository {
        override suspend fun getListFirst(): Resource<ListResponse> {
            return handleAPICall { dataSource.getListFirst() }
        }

        override suspend fun getListSecond(): Resource<ListResponse> {
            return handleAPICall { dataSource.getListSecond() }
        }
    }
