package com.example.kmmjsonplaceholderapp.core.domain.repo

import com.example.kmmjsonplaceholderapp.core.domain.models.Post
import com.example.kmmjsonplaceholderapp.core.domain.models.PostComments
import kotlinx.coroutines.flow.Flow

interface PostRepo {

    suspend fun refreshPosts(userId: Int): Result<Unit>

    suspend fun getFlowPostsByUserId(userId: Int): Flow<List<PostComments>>

    suspend fun updatePost(updatedPost: Post): Result<Unit>

    suspend fun addPost(content: String, userId: Int): Result<Unit>

    suspend fun deletePostById(postId: Int): Result<Unit>

    suspend fun deleteCommentById(commentId: Int): Result<Unit>
}