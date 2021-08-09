package com.myisolutions.imagegallery.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.myisolutions.imagegallery.R
import com.myisolutions.imagegallery.data.model.PixaResponse
import com.myisolutions.imagegallery.databinding.ImageItemBinding
import java.util.*

class ImageGalleryAdapter(private val listener: OnItemClickListener) :
    PagingDataAdapter<PixaResponse.Hits, ImageGalleryAdapter.ImageViewHolder>(IMAGE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = DataBindingUtil.inflate<ImageItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.image_item,
            parent,
            false
        )
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class ImageViewHolder(private val binding: ImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(hit: PixaResponse.Hits) {
            binding.apply {
                this.hit = hit
                this.listener = this@ImageGalleryAdapter.listener
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(hit: PixaResponse.Hits)
    }

    companion object {
        private val IMAGE_COMPARATOR = object : DiffUtil.ItemCallback<PixaResponse.Hits>() {
            override fun areItemsTheSame(oldItem: PixaResponse.Hits, newItem: PixaResponse.Hits) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: PixaResponse.Hits,
                newItem: PixaResponse.Hits
            ) =
                oldItem == newItem
        }
    }
}