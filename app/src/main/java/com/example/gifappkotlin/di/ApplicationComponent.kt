package com.example.gifappkotlin.di

import android.content.Context
import com.example.gifappkotlin.view.ui.main.FavoritesFragment
import com.example.gifappkotlin.view.ui.main.MainActivity
import com.example.gifappkotlin.view.ui.main.GifListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(gifListFragment: GifListFragment)

    fun inject(favoritesFragment: FavoritesFragment)

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context) : ApplicationComponent
    }
}