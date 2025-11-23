package com.ezbuy.data.repository

import com.ezbuy.common.utils.Resource
import com.ezbuy.data.model.ListResponse

interface ListRepository {
    suspend fun getListFirst(): Resource<ListResponse>

    suspend fun getListSecond(): Resource<ListResponse>
}
