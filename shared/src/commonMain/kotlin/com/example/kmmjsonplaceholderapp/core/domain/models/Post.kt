package com.example.kmmjsonplaceholderapp.core.domain.models

data class Post(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int,
    val username: String
)