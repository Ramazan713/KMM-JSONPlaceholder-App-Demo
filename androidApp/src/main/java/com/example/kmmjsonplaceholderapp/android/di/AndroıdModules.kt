package com.example.kmmjsonplaceholderapp.android.di

import org.koin.core.module.Module

fun getAndroidModules(): List<Module>{
    return listOf(
        appModule,
        viewModelModule
    )
}