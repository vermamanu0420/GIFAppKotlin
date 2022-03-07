package com.example.gifappkotlin.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavouriteGif::class], version = 1)
abstract class FavouriteGifsDatabase : RoomDatabase() {
    abstract fun FavouritesDao(): FavouriteDAO
}