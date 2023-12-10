package com.example.kmmjsonplaceholderapp.core.data.repo

import com.example.kmmjsonplaceholderapp.core.data.local.service.UserDataSource
import com.example.kmmjsonplaceholderapp.core.data.mapper.toUser
import com.example.kmmjsonplaceholderapp.core.data.mapper.toUserEntity
import com.example.kmmjsonplaceholderapp.core.data.remote.KtorApi
import com.example.kmmjsonplaceholderapp.core.domain.models.User
import com.example.kmmjsonplaceholderapp.core.domain.repo.UserRepo
import com.example.kmmjsonplaceholderapp.core.domain.utils.ExceptionHandler
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class UserRepoImpl constructor(
    private val api: KtorApi,
    private val userDataSource: UserDataSource,
    private val exceptionHandler: ExceptionHandler
): UserRepo {
    override suspend fun refreshUsers(): Result<Unit> {
        return exceptionHandler.coHandle(
            onError = { Result.failure(it) }
        ){
            val userEntitiesFromDto = api.getUsers().map { it.toUserEntity() }
            withContext(NonCancellable){
                userDataSource.insertUsers(userEntitiesFromDto)
            }
            Result.success(Unit)
        }
    }

    override fun getFlowUsers(): Flow<List<User>> {
        return userDataSource.getUsers()
            .map { users->
                users.map {
                    it.toUser()
                }
            }
    }

    override suspend fun deleteUser(user: User): Result<Unit> {
        return exceptionHandler.coHandle(
            onError = { Result.failure(it) }
        ){
            api.deleteUserById(user.id)
            withContext(NonCancellable){
                userDataSource.deleteUserById(user.id)
            }
            Result.success(Unit)
        }
    }
}