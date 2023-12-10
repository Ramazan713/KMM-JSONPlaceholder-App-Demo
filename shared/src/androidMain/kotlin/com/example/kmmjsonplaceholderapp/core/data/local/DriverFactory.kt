package com.example.kmmjsonplaceholderapp.core.data.local

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.AppDatabase

actual class DriverFactory(private val context: Context) {

    actual fun createDriver(): SqlDriver{
        return AndroidSqliteDriver(
            AppDatabase.Schema,
            context,
            "appDb.db",
            callback = object : AndroidSqliteDriver.Callback(AppDatabase.Schema){
                override fun onOpen(db: SupportSQLiteDatabase) {
                    db.setForeignKeyConstraintsEnabled(true)
                }
            }
        )
    }
}