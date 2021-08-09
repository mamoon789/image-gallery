package com.myisolutions.imagegallery.ui.view.fragment

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.myisolutions.imagegallery.R
import com.myisolutions.imagegallery.databinding.FragmentImageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageFragment : Fragment(R.layout.fragment_image) {
    private val args by navArgs<ImageFragmentArgs>()

    private var _binding: FragmentImageBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = DataBindingUtil.bind(view)
        binding.hit = args.hit

        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}