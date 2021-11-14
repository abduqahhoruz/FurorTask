package com.example.furortask.business.data.remote

import com.example.furortask.business.data.remote.model.GetProductResponseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("product/")
    suspend fun getProductTypes(
        @Query("page") page: Int,
        @Query("perpage") perpage: Int
    ):
            List<GetProductResponseData>
}