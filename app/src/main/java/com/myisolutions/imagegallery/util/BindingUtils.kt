package com.myisolutions.imagegallery.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.myisolutions.imagegallery.R


@BindingAdapter("url")
fun loadImage(view: ImageView, url: String?) =
    Glide.with(view)
        .load(Constants.IMG_BASE_URL + url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .error(R.drawable.error)
        .into(view)
