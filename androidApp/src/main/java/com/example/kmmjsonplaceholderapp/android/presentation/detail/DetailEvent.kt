package com.example.kmmjsonplaceholderapp.android.presentation.detail

import com.example.kmmjsonplaceholderapp.core.domain.models.Comment
import com.example.kmmjsonplaceholderapp.core.domain.models.Post


sealed interface DetailEvent {

    data object Refresh: DetailEvent

    data class DeletePost(val id: Int): DetailEvent

    data class DeleteComment(val comment: Comment): DetailEvent

    data class AddPost(val body: String): DetailEvent

    data class UpdatePost(val content: String, val oldPost: Post): DetailEvent

    data class ShowDialog(val event: DetailDialogEvent?): DetailEvent

    data object ClearMessage: DetailEvent
}