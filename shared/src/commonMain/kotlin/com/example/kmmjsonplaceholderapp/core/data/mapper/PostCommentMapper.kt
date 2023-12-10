package com.example.kmmjsonplaceholderapp.core.data.mapper

import com.example.kmmjsonplaceholderapp.core.data.local.relation.PostCommentsRelation
import com.example.kmmjsonplaceholderapp.core.domain.models.PostComments

fun PostCommentsRelation.toPostComments(): PostComments {
    return PostComments(
        post = post.toPost(),
        comments = comments.map { it.toComment() }
    )
}