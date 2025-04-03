package com.ykcoding.recipefinderapp

import android.app.Application
import android.content.Context
import com.ykcoding.recipefinderapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class RecipeFinderApp: Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin(this@RecipeFinderApp)
    }

    private fun setupKoin(androidContext: Context) {
        startKoin {
            androidContext(androidContext)
            androidLogger(level = Level.DEBUG)
            modules(appModule)
        }
    }
}