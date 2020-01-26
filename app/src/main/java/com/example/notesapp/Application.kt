package com.example.notesapp

import com.example.notesapp.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Created by Richard Gross on 2020-01-14
 */
open class Application : DaggerApplication() {

    companion object {
        lateinit var instance: Application
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.factory().create(applicationContext)
    }

}