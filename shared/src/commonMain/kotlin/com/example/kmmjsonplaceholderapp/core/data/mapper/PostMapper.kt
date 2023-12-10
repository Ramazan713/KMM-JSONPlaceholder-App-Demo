package com.example.kmmjsonplaceholderapp.core.data.mapper

import com.example.DetailPostView
import com.example.Posts
import com.example.kmmjsonplaceholderapp.core.data.remote.dto.PostDtoRequest
import com.example.kmmjsonplaceholderapp.core.data.remote.dto.PostDtoResponse
import com.example.kmmjsonplaceholderapp.core.domain.models.Post

fun Post.toPostDtoRequest(): PostDtoRequest {
    return PostDtoRequest(
        body, id, title, userId
    )
}

fun PostDtoResponse.toPostEntity(): Posts {
    return Posts(
        id.toLong(), userId.toLong(), body, title,
    )
}

fun DetailPostView.toPost(): Post {
    return Post(
        id = id.toInt(),
        userId = userId.toInt(),
        body = body,
        title = title,
        username = username
    )
}