package com.example.kmmjsonplaceholderapp.android.presentation.home

import com.example.kmmjsonplaceholderapp.core.domain.models.User


sealed interface HomeEvent {

    data object Refresh: HomeEvent

    data class Delete(val user: User): HomeEvent

    data object ClearMessage: HomeEvent

}