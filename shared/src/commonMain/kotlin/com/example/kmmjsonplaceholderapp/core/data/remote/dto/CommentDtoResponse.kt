package com.example.kmmjsonplaceholderapp.core.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CommentDtoResponse(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)