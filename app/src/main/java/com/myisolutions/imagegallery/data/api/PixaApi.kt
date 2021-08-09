package com.myisolutions.imagegallery.data.api

import com.myisolutions.imagegallery.data.model.PixaResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaApi {

    @GET(".")
    suspend fun searchImages(
        @Query("key") key: String,
        @Query("q") query: String,
        @Query("image_type") image_type: String,
        @Query("page") page: Int
    ): PixaResponse

}