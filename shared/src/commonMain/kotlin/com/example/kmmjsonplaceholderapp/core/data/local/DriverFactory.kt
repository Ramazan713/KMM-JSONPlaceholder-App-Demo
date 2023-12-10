package com.example.kmmjsonplaceholderapp.core.data.local

import app.cash.sqldelight.db.SqlDriver
import com.example.AppDatabase


expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory): AppDatabase {
    return AppDatabase(driverFactory.createDriver())
}
