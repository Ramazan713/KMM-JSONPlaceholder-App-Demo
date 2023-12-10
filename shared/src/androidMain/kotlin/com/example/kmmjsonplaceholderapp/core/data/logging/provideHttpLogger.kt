package com.example.kmmjsonplaceholderapp.core.data.logging

import android.util.Log
import io.ktor.client.plugins.logging.Logger

internal class CustomAndroidHttpLogger : Logger {
    private val logTag = "asdasdsadsadasdda"

    override fun log(message: String) {
        Log.i(logTag, message)
    }
}

internal actual fun provideHttpLogger(): Logger {
    return CustomAndroidHttpLogger()
}