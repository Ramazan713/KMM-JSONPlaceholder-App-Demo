package com.example.kmmjsonplaceholderapp.core.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.example.AppDatabase

actual class DriverFactory {
    actual fun createDriver(): SqlDriver{
        return NativeSqliteDriver(AppDatabase.Schema,"appDb.db")
    }
}