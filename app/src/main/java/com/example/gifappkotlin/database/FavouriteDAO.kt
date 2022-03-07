package com.example.gifappkotlin.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavouriteDAO {

    @Query("SELECT * FROM favourite_gifs")
    suspend fun getAll(): List<FavouriteGif>

    @Insert
    suspend fun insert(favouriteGif: FavouriteGif?)

    @Delete
    suspend fun delete(favouriteGif: FavouriteGif?)

    @Query("DELETE FROM favourite_gifs where gif_id =:gifId")
    suspend fun deleteItem(gifId: String?)

    @Query("select Count() from favourite_gifs where gif_id =:gifId")
    suspend fun checkArticle(gifId: String?): Int
}