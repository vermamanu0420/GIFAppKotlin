package com.example.gifappkotlin.di

import com.example.gifappkotlin.view.ui.main.MainActivity
import com.example.gifappkotlin.view.ui.main.GifListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(gifListFragment: GifListFragment)

}