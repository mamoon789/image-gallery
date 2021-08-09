package com.myisolutions.imagegallery.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.myisolutions.imagegallery.data.api.PixaApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PixaRepository @Inject constructor(private val api: PixaApi) {

    fun getSearchResults(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PixaPagingSource(api, query)
            }
        ).liveData
}