package com.example.kmmjsonplaceholderapp.di

import com.example.kmmjsonplaceholderapp.core.data.local.DriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

private val appModule = module {
    single {
        DriverFactory()
    }
}


fun getIosModules(): List<Module>{
    return listOf(appModule)
}