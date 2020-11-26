package com.example.notesapp

import android.app.Application
import com.example.notesapp.di.allKoinDependentModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by Richard Gross on 2020-01-14
 */
open class App : Application() {

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(allKoinDependentModules)
        }
    }
}