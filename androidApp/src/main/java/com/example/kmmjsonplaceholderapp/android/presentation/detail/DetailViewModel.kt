package com.example.kmmjsonplaceholderapp.android.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmmjsonplaceholderapp.core.domain.repo.PostRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class DetailArgs(val userId: Int){
    constructor(savedStateHandle: SavedStateHandle): this(
        checkNotNull(savedStateHandle["userId"]) as Int
    )
}


class DetailViewModel constructor(
    private val postRepo: PostRepo,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()

    val args = DetailArgs(savedStateHandle)

    init {
        loadData()
    }

    fun onEvent(event: DetailEvent){
        when(event){
            is DetailEvent.AddPost -> {
                handleRequest {
                    postRepo.addPost(event.body,args.userId)
                }
            }
            is DetailEvent.DeletePost -> {
                handleRequest {
                    postRepo.deletePostById(event.id)
                }
            }
            DetailEvent.Refresh -> {
                handleRequest {
                    postRepo.refreshPosts(args.userId)
                }
            }
            is DetailEvent.UpdatePost -> {
                handleRequest {
                    val updatedPost = event.oldPost.copy(body = event.content)
                    postRepo.updatePost(updatedPost)
                }
            }
            is DetailEvent.ShowDialog -> {
                _state.update {
                    it.copy(dialogEvent = event.event)
                }
            }

            is DetailEvent.DeleteComment -> {
                handleRequest {
                    postRepo.deleteCommentById(event.comment.id)
                }
            }
            DetailEvent.ClearMessage -> {
                _state.update { it.copy(message = null) }
            }
        }
    }


    private fun<T> handleRequest( execute: suspend () -> Result<T>){
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val result = execute()

            result.onFailure {error->
                _state.update { it.copy(
                    message = error.localizedMessage,
                    isLoading = false
                ) }
            }
            result.onSuccess {
                _state.update { it.copy(
                    isLoading = false
                ) }
            }
        }
    }

    private fun loadData(){
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            postRepo.getFlowPostsByUserId(args.userId).collectLatest { posts->
                if(posts.isEmpty()){
                    onEvent(DetailEvent.Refresh)
                }
                _state.update {
                    it.copy(
                        isLoading = false,
                        items = posts
                    )
                }
            }

        }
    }
}