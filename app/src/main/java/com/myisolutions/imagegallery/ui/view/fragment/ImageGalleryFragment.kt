package com.myisolutions.imagegallery.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.myisolutions.imagegallery.R
import com.myisolutions.imagegallery.data.model.PixaResponse
import com.myisolutions.imagegallery.databinding.FragmentImageGalleryBinding
import com.myisolutions.imagegallery.ui.view.adapter.ImageGalleryAdapter
import com.myisolutions.imagegallery.ui.viewmodel.ImageGalleryViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

@AndroidEntryPoint
class ImageGalleryFragment : Fragment(R.layout.fragment_image_gallery),
    ImageGalleryAdapter.OnItemClickListener {
    private val viewModel by viewModels<ImageGalleryViewModel>()

    private var _binding: FragmentImageGalleryBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentImageGalleryBinding.bind(view)

        val adapter = ImageGalleryAdapter(this)

        binding.apply {
            rvImages.setHasFixedSize(true)
            rvImages.adapter = adapter

            svImages.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        rvImages.scrollToPosition(0)
                        viewModel.searchImages(query)
                        svImages.clearFocus()
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
        }

        try {
            viewModel.images.observe(viewLifecycleOwner) {
                adapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        } catch (e: Exception) {
        }
    }

    override fun onItemClick(hit: PixaResponse.Hits) {
        val action = ImageGalleryFragmentDirections.navigateToImageFragment(hit)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}