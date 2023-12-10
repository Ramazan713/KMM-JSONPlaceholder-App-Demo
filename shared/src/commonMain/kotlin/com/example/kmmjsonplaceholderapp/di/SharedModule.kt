package com.example.kmmjsonplaceholderapp.di

import org.koin.core.module.Module

fun getSharedModule(): List<Module>{
    return listOf(
        repoModule,
        remoteModule,
        databaseModule,
        appModule
    )
}