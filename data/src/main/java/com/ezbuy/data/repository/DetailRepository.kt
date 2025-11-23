package com.ezbuy.data.repository

import com.ezbuy.common.utils.Resource
import com.ezbuy.data.model.DetailResponse

interface DetailRepository {
    suspend fun getDetail(): Resource<DetailResponse>
}