package com.example.gifappkotlin.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gifappkotlin.model.GifData
import com.example.gifappkotlin.retrofit.GifApi
import com.example.gifappkotlin.utils.Constants.API_KEY
import com.example.gifappkotlin.utils.Constants.GIF_RATING
import javax.inject.Inject

class GifRepository @Inject constructor(private val gifApi: GifApi){

    private val _gifsData = MutableLiveData<List<GifData>>();
    // used live data to expose only ready only data
   val gifsData: LiveData<List<GifData>>
   get() = _gifsData

    suspend fun getGifs(query: String, offset: Int, limit: Int){
        val result = gifApi.getGifs(API_KEY, query,offset,limit)
        if (result.isSuccessful && result.body() != null){
            _gifsData.postValue(result.body()!!.data)
        }
    }

    suspend fun getTrendingGifs(offset: Int, limit: Int){
        val result = gifApi.getTrendingGifs(API_KEY,offset, GIF_RATING,limit)
        if (result.isSuccessful && result.body() != null){
            _gifsData.postValue(result.body()!!.data)
        }
    }
}