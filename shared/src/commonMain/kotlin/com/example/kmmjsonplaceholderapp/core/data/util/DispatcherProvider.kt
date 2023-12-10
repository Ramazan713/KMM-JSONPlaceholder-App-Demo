package com.example.kmmjsonplaceholderapp.core.data.util

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider{
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
}

internal expect fun provideDispatcher(): DispatcherProvider
