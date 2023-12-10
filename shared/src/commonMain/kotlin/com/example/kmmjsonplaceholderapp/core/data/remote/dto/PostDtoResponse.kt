package com.example.kmmjsonplaceholderapp.core.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostDtoResponse(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)