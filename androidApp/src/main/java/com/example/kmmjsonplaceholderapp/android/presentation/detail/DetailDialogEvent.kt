package com.example.kmmjsonplaceholderapp.android.presentation.detail

import com.example.kmmjsonplaceholderapp.core.domain.models.Post

sealed interface DetailDialogEvent {

    data object AddPost: DetailDialogEvent

    data class UpdatePost(val post: Post): DetailDialogEvent
}