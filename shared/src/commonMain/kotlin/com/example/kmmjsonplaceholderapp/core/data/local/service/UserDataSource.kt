package com.example.kmmjsonplaceholderapp.core.data.local.service

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.example.UserQueries
import com.example.Users
import com.example.kmmjsonplaceholderapp.core.data.util.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class UserDataSource constructor(
    private val userQueries: UserQueries,
    private val dispatcherProvider: DispatcherProvider
)  {

    fun getUsers(): Flow<List<Users>>{
        return userQueries.getUsers()
            .asFlow()
            .mapToList(dispatcherProvider.io)
    }

    suspend fun deleteUserById(id: Int){
        withContext(dispatcherProvider.io){
            userQueries.deleteUserById(id.toLong())
        }
    }

    suspend fun insertUsers(users: List<Users>){
        withContext(dispatcherProvider.io){
            userQueries.transaction {
                users.forEach { user->
                    userQueries.insertUser(user)
                }
            }
        }
    }
}