package com.example.gifappkotlin.retrofit

import com.example.gifappkotlin.model.GifsDataModel
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface GifApi {

    @GET("v1/gifs/search")
    suspend fun getGifs(
        @Query("api_key") apiKey: String?,
        @Query("q") searchTerm: String?,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<GifsDataModel>

    @GET("v1/gifs/trending")
    suspend fun getTrendingGifs(
        @Query("api_key") apiKey: String?,
        @Query("offset") offset: Int,
        @Query("rating") rating: String?,
        @Query("limit") limit: Int
    ): Response<GifsDataModel>
}