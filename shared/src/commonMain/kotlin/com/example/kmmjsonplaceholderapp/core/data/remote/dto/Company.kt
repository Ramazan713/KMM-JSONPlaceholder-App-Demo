package com.example.kmmjsonplaceholderapp.core.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class Company(
    val bs: String,
    val catchPhrase: String,
    val name: String
)