package com.example.kmmjsonplaceholderapp.core.domain.repo

import com.example.kmmjsonplaceholderapp.core.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepo {

    suspend fun refreshUsers(): Result<Unit>

    fun getFlowUsers(): Flow<List<User>>

    suspend fun deleteUser(user: User): Result<Unit>

}