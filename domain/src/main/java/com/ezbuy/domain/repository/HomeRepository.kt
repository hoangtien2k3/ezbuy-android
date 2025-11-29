package com.ezbuy.domain.repository

import com.ezbuy.common.utils.Resource
import com.ezbuy.domain.model.home.HomeModel

interface HomeRepository {
    suspend fun getHome(): Resource<HomeModel>
}
