package com.myisolutions.imagegallery.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class PixaResponse(
    val hits: List<Hits>
) {
    @Parcelize
    data class Hits(
        val id: Int,
        val webformatURL: String
    ) : Parcelable
}