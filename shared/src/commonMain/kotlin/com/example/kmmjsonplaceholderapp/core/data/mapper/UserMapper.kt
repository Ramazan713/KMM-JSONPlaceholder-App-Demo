package com.example.kmmjsonplaceholderapp.core.data.mapper

import com.example.Users
import com.example.kmmjsonplaceholderapp.core.data.remote.dto.UserDtoResponse
import com.example.kmmjsonplaceholderapp.core.domain.models.User

fun UserDtoResponse.toUser(): User {
    return User(id, name, username, email)
}

fun UserDtoResponse.toUserEntity(): Users{
    return Users(id.toLong(), name, username, email)
}

fun Users.toUser(): User{
    return User(id.toInt(), name, username, email)
}