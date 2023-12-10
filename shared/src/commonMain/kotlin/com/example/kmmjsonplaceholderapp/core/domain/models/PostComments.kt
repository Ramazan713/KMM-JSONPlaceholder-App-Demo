package com.example.kmmjsonplaceholderapp.core.domain.models

import com.example.kmmjsonplaceholderapp.core.domain.models.Comment
import com.example.kmmjsonplaceholderapp.core.domain.models.Post

data class PostComments(
    val post: Post,
    val comments: List<Comment>
)
