package com.ezbuy.data.repository

import com.ezbuy.common.utils.Resource
import com.ezbuy.data.model.HomeResponse

interface HomeRepository {
    suspend fun getHome(): Resource<HomeResponse>
}