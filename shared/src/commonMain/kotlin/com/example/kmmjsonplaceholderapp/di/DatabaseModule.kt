package com.example.kmmjsonplaceholderapp.di
import com.example.AppDatabase
import com.example.kmmjsonplaceholderapp.core.data.local.createDatabase
import com.example.kmmjsonplaceholderapp.core.data.local.service.PostDataSource
import com.example.kmmjsonplaceholderapp.core.data.local.service.UserDataSource
import org.koin.dsl.module


internal val databaseModule = module {

    single {
        createDatabase(get())
    }

    single {
        PostDataSource(get(),get())
    }

    single {
        UserDataSource(get<AppDatabase>().userQueries,get())
    }
}