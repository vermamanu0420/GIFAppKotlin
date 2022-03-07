package com.example.gifappkotlin.di

import android.content.Context
import androidx.room.Room
import com.example.gifappkotlin.database.FavouriteGifsDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideFavouriteDB(context: Context): FavouriteGifsDatabase{
        return Room.databaseBuilder(context,FavouriteGifsDatabase::class.java, "favouritesDb").build()
    }
}