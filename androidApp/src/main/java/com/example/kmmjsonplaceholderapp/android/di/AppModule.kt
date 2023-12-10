package com.example.kmmjsonplaceholderapp.android.di

import com.example.kmmjsonplaceholderapp.core.data.local.DriverFactory
import org.koin.dsl.module

internal val appModule = module {
    single {
        DriverFactory(get())
    }
}