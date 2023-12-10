package com.example.kmmjsonplaceholderapp.core.data.logging

import io.ktor.client.plugins.logging.Logger

internal class CustomNativeHttpLogger : Logger {
    override fun log(message: String) {

    }
}

internal actual fun provideHttpLogger(): Logger {
    return CustomNativeHttpLogger()
}