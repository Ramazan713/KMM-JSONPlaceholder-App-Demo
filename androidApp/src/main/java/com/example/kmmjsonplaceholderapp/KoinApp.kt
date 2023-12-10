package com.example.kmmjsonplaceholderapp

import android.app.Application
import com.example.kmmjsonplaceholderapp.android.di.appModule
import com.example.kmmjsonplaceholderapp.android.di.getAndroidModules
import com.example.kmmjsonplaceholderapp.android.di.viewModelModule
import com.example.kmmjsonplaceholderapp.di.getSharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@KoinApp)

            modules(getSharedModule() + getAndroidModules())
        }
    }
}