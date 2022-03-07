package com.example.gifappkotlin

import android.app.Application
import com.example.gifappkotlin.di.ApplicationComponent
import com.example.gifappkotlin.di.DaggerApplicationComponent

class GifApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent;

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }

}