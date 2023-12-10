package com.example.kmmjsonplaceholderapp.di

import com.example.kmmjsonplaceholderapp.core.data.util.provideDispatcher
import com.example.kmmjsonplaceholderapp.core.domain.utils.ExceptionHandler
import org.koin.dsl.module


internal val appModule = module {
    factory {
        ExceptionHandler()
    }

    single {
        provideDispatcher()
    }
}

