package com.example.kmmjsonplaceholderapp.core.data.local.service

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.example.AppDatabase
import com.example.Comments
import com.example.Posts
import com.example.kmmjsonplaceholderapp.core.data.local.relation.PostCommentsRelation
import com.example.kmmjsonplaceholderapp.core.data.util.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class PostDataSource constructor(
    private val db: AppDatabase,
    private val dispatcherProvider: DispatcherProvider
) {
    private val postViewQueries = db.postViewQueries
    private val postQueries = db.postQueries
    private val commentsQueries = db.commentsQueries

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getPostsByUserId(userId: Int): Flow<List<PostCommentsRelation>> {
        return postViewQueries.getDetailPostsByUserId(userId.toLong())
            .asFlow()
            .mapToList(dispatcherProvider.io)
            .flatMapLatest {posts->
                if(posts.isEmpty()){
                    return@flatMapLatest flow { emit(emptyList()) }
                }

                val commentsListFlow = posts.map { post->
                    commentsQueries.getCommentsByPostId(post.id)
                        .asFlow()
                        .mapToList(dispatcherProvider.io)
                        .map {comments->
                            PostCommentsRelation(post,comments)
                        }
                }
                combine(commentsListFlow){
                    it.toList()
                }
            }
    }


    suspend fun deletePostById(id: Int){
        withContext(dispatcherProvider.io){
            postQueries.deletePostById(id.toLong())
        }
    }

    suspend fun insertPost(post: Posts){
        withContext(dispatcherProvider.io){
            postQueries.insertPost(post)
        }
    }

    suspend fun updatePost(post: Posts){
        withContext(dispatcherProvider.io){
            postQueries.updatePost(post.body,post.title,post.id)
        }
    }

    suspend fun deleteCommentById(commentId: Int){
        withContext(dispatcherProvider.io){
            commentsQueries.deleteById(commentId.toLong())
        }
    }

    suspend fun insertData(comments: List<Comments>, posts: List<Posts>){
        withContext(dispatcherProvider.io){
            db.transaction {
                posts.forEach {
                    postQueries.insertPost(it)
                }
                comments.forEach {
                    commentsQueries.insertComment(it)
                }
            }
        }
    }
}