package com.ezbuy.data.remote

import com.ezbuy.data.model.DetailResponse
import com.ezbuy.data.model.HomeResponse
import com.ezbuy.data.model.ListResponse
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("/basaransuleyman/suleyman-basaranoglu-json/main/json-home-page")
    suspend fun getHome(): Response<HomeResponse>

    @GET("/basaransuleyman/suleyman-basaranoglu-json/main/detail-page")
    suspend fun getDetail(): Response<DetailResponse>

    @GET("/basaransuleyman/suleyman-basaranoglu-json/main/list-page-paging-first")
    suspend fun getListPageFirst(): Response<ListResponse>

    @GET("/basaransuleyman/suleyman-basaranoglu-json/main/list-page-paging-second")
    suspend fun getListPageSecond(): Response<ListResponse>
}