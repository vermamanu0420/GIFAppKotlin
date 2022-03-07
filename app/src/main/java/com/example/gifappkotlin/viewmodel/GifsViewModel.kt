package com.example.gifappkotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gifappkotlin.model.GifData
import com.example.gifappkotlin.repository.GifRepository
import kotlinx.coroutines.launch

class GifsViewModel(private val repository: GifRepository) : ViewModel() {

    val gifsLiveData : LiveData<List<GifData>>
    get() = repository.gifsData

    init {
        viewModelScope.launch{
            repository.getTrendingGifs(0, 20)
        }
    }
}