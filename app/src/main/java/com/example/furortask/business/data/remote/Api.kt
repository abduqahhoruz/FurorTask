package com.example.furortask.business.data.remote

import com.example.furortask.business.data.remote.model.GetProductResponseData
import com.example.furortask.business.data.remote.model.PutProductRequstModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {
    @GET("product/")
    suspend fun getProductTypes(
        @Query("page") page: Int,
        @Query("perpage") perpage: Int
    ):
            List<GetProductResponseData>

    @POST("product/")
    suspend fun putProduct(@Body putProductModel: PutProductRequstModel)
    : Response<Any>
}