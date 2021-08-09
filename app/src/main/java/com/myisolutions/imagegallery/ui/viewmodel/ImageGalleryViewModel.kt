package com.myisolutions.imagegallery.ui.viewmodel

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.myisolutions.imagegallery.data.repository.PixaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageGalleryViewModel @Inject constructor(private val repository: PixaRepository) : ViewModel() {
    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    val images = currentQuery.switchMap {
        repository.getSearchResults(it).cachedIn(viewModelScope)
    }

    fun searchImages(query: String) {
        currentQuery.value = query
    }

    companion object{
        private const val DEFAULT_QUERY = "yellow flowers"
    }
}