package com.example.kmmjsonplaceholderapp.android.presentation.detail

import com.example.kmmjsonplaceholderapp.core.domain.models.PostComments


data class DetailState(
    val isLoading: Boolean = false,
    val items: List<PostComments> = emptyList(),
    val dialogEvent: DetailDialogEvent? = null,
    val message: String? = null,
)
