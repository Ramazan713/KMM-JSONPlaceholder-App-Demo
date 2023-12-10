package com.example.kmmjsonplaceholderapp.core.data.mapper

import com.example.Comments
import com.example.kmmjsonplaceholderapp.core.data.remote.dto.CommentDtoResponse
import com.example.kmmjsonplaceholderapp.core.domain.models.Comment

fun CommentDtoResponse.toEntity(): Comments{
    return Comments(
        id = id.toLong(),
        postId = postId.toLong(),
        name, body, email
    )
}


fun Comments.toComment(): Comment {
    return Comment(
        id = id.toInt(),
        postId = postId.toInt(),
        name = name,
        body = body,
        email = email
    )
}