package com.example.gifappkotlin.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gifappkotlin.database.FavouriteGif
import com.example.gifappkotlin.database.FavouriteGifsDatabase
import com.example.gifappkotlin.model.GifData
import com.example.gifappkotlin.retrofit.GifApi
import com.example.gifappkotlin.utils.Constants.API_KEY
import com.example.gifappkotlin.utils.Constants.GIF_RATING
import java.util.ArrayList
import javax.inject.Inject

class GifRepository @Inject constructor(private val gifApi: GifApi, private val favouriteGifsDatabase: FavouriteGifsDatabase){

    private val _gifsData = MutableLiveData<List<GifData>>();
    private val _favouriteGifsData = MutableLiveData<List<FavouriteGif>>();
    private val currentGifsList = ArrayList<GifData>()
    private var offset = 0
    private val LIMIT = 20
    // used live data to expose only ready only data
   val gifsData: LiveData<List<GifData>>
   get() = _gifsData

    val favouriteGifsData: LiveData<List<FavouriteGif>>
        get() = _favouriteGifsData

    suspend fun getGifs(query: String, resetOffset: Boolean){
        if (resetOffset) {
            currentGifsList.clear()
            offset = 0
        } else offset += LIMIT

        val result = gifApi.getGifs(API_KEY, query,offset,LIMIT)
        if (result.isSuccessful && result.body() != null){
            currentGifsList.addAll(result.body()!!.data)
            UpdateFavourites()
        }
    }

    suspend fun getTrendingGifs( resetOffset: Boolean){
        if (resetOffset) {
            currentGifsList.clear()
            offset = 0
        } else offset += LIMIT
        val result = gifApi.getTrendingGifs(API_KEY,offset, GIF_RATING,LIMIT)
        if (result.isSuccessful && result.body() != null){
            currentGifsList.addAll(result.body()!!.data)
            UpdateFavourites()
        }
    }

    suspend fun InsertFavourite(fav: FavouriteGif?) {
        favouriteGifsDatabase.FavouritesDao().insert(fav)
        getFavouritesFromDb()
        UpdateFavourites()
    }

    suspend fun DeleteFavourite(item: FavouriteGif?) {
        favouriteGifsDatabase.FavouritesDao().delete(item)
        getFavouritesFromDb()
        UpdateFavourites()
    }

    suspend fun DeleteFavouriteBYId(gifId: String?) {
        favouriteGifsDatabase.FavouritesDao().deleteItem(gifId)
        UpdateFavourites()
    }

    private suspend fun UpdateFavourites() {
        for (i in currentGifsList.indices) {
            val count: Int =  favouriteGifsDatabase.FavouritesDao().checkArticle(currentGifsList[i].id)
            currentGifsList[i].IsFavourite = count > 0
        }
        _gifsData.postValue(currentGifsList)
    }

    suspend fun getFavouritesFromDb(){
        _favouriteGifsData.postValue(favouriteGifsDatabase.FavouritesDao().getAll())
    }
}