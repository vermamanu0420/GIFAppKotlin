package com.example.gifappkotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gifappkotlin.repository.GifRepository
import javax.inject.Inject


class GifsViewModelFactory @Inject constructor(private val repository: GifRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GifsViewModel(repository) as T
    }
}