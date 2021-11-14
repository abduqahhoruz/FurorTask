package com.example.furortask.presentation.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.furortask.business.data.remote.Api
import com.example.furortask.business.data.remote.model.GetProductResponseData
import retrofit2.HttpException
import java.io.IOException

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class ProductPagingSource(
    private val api: Api,
) : PagingSource<Int, GetProductResponseData>() {

    override fun getRefreshKey(state: PagingState<Int, GetProductResponseData>): Int? {
        return 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GetProductResponseData> {
        return try {
            val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

            val responseData = api.getProductTypes(position, params.loadSize)
            //TODO
            return LoadResult.Page(
                data = responseData,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (responseData.isEmpty()) null else position + 1

            )

        }
        catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}