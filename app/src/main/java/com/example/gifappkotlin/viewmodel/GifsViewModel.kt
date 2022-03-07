package com.example.gifappkotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gifappkotlin.database.FavouriteGif
import com.example.gifappkotlin.model.GifData
import com.example.gifappkotlin.repository.GifRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import java.util.ArrayList

class GifsViewModel(private val repository: GifRepository) : ViewModel() {

    var dataLoadError = MutableLiveData<Boolean>()
    var loading = MutableLiveData<Boolean>()

    val gifsLiveData : LiveData<List<GifData>>
    get() = repository.gifsData

    val gifsFavouriteGifLiveData : LiveData<List<FavouriteGif>>
        get() = repository.favouriteGifsData

    init {
        viewModelScope.launch{
            repository.getFavouritesFromDb()
        }
    }

    fun fetchGifs(searchTerm: String, resetOffset: Boolean) {
        loading.value = true
        viewModelScope.launch{
            repository.getGifs(searchTerm, resetOffset)
        }
    }
    fun fetchTrendingGifs(resetOffset: Boolean) {
        loading.value = true
        viewModelScope.launch{
            repository.getTrendingGifs(resetOffset)
        }
    }

    fun InsertFavourite(fav: FavouriteGif) {
        viewModelScope.launch{
            repository.InsertFavourite(fav)
        }
    }

    fun DeleteFavourite(fav: FavouriteGif?) {
        viewModelScope.launch{
            repository.DeleteFavourite(fav)
        }
    }

    fun DeleteFavouriteBYId(gifId: String?) {
        viewModelScope.launch{
            repository.DeleteFavouriteBYId(gifId)
        }
    }
}