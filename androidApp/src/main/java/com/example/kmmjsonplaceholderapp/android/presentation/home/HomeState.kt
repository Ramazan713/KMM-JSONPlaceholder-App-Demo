package com.example.kmmjsonplaceholderapp.android.presentation.home

import com.example.kmmjsonplaceholderapp.core.domain.models.User


data class HomeState(
    val isLoading: Boolean = false,
    val items: List<User> = emptyList(),
    val message: String? = null
)
