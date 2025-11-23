package com.ezbuy.data.remote.datasource

import com.ezbuy.data.model.DetailResponse
import com.ezbuy.data.model.HomeResponse
import com.ezbuy.data.model.ListResponse
import retrofit2.Response

interface DataSource {
    suspend fun getHome(): Response<HomeResponse>
    suspend fun getDetail(): Response<DetailResponse>
    suspend fun getListFirst(): Response<ListResponse>
    suspend fun getListSecond(): Response<ListResponse>
}