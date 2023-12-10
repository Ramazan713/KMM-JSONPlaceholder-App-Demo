package com.example.kmmjsonplaceholderapp.util

import com.example.kmmjsonplaceholderapp.di.getIosModules
import com.example.kmmjsonplaceholderapp.di.getSharedModule
import org.koin.core.context.startKoin

fun initKoin(){
    startKoin {
        modules(getSharedModule() + getIosModules())
    }
}