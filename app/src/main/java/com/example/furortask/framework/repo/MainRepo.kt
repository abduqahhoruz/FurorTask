package com.example.furortask.framework.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.furortask.business.data.remote.Api
import com.example.furortask.business.data.remote.model.PutProductRequstModel
import com.example.furortask.presentation.paging.ProductPagingSource

class MainRepo(private val service: Api) {

    fun getProductResult() =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ProductPagingSource(service) }
        ).liveData
    suspend fun putProduct(putProductRequstModel: PutProductRequstModel) = service.putProduct(putProductRequstModel)
}