package com.example.kmmjsonplaceholderapp.core.data.remote.dto

import com.example.kmmjsonplaceholderapp.core.data.remote.dto.Address
import com.example.kmmjsonplaceholderapp.core.data.remote.dto.Company
import kotlinx.serialization.Serializable

@Serializable
data class UserDtoResponse(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)