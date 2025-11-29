package com.ezbuy.data.remote.datasource

import com.ezbuy.data.model.HomeResponse
import retrofit2.Response

interface DataSource {
    suspend fun getHome(): Response<HomeResponse>
}
