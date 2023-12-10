package com.example.kmmjsonplaceholderapp.core.data.logging

import io.ktor.client.plugins.logging.Logger

internal expect fun provideHttpLogger(): Logger