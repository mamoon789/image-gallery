package com.myisolutions.imagegallery.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.myisolutions.imagegallery.data.api.PixaApi
import com.myisolutions.imagegallery.data.model.PixaResponse
import com.myisolutions.imagegallery.util.Constants.API_KEY
import retrofit2.HttpException
import java.io.IOException

const val STARTING_PAGING_INDEX = 1
const val IMG_TYPE = "photo"

class PixaPagingSource(
    private val api: PixaApi,
    private val query: String
) : PagingSource<Int, PixaResponse.Hits>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PixaResponse.Hits> {
        val position = params.key ?: STARTING_PAGING_INDEX
        return try {
            val response = api.searchImages(API_KEY, query, IMG_TYPE, position)
            val images = response.hits

            LoadResult.Page(
                data = images,
                prevKey = if (position == STARTING_PAGING_INDEX) null else position - 1,
                nextKey = if (images.isNullOrEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PixaResponse.Hits>): Int? {
        return state.anchorPosition
    }
}