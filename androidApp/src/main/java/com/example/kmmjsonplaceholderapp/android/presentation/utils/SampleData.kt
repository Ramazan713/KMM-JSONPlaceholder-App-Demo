package com.example.kmmjsonplaceholderapp.android.presentation.utils

import com.example.kmmjsonplaceholderapp.core.domain.models.Comment
import com.example.kmmjsonplaceholderapp.core.domain.models.Post
import com.example.kmmjsonplaceholderapp.core.domain.models.PostComments
import com.example.kmmjsonplaceholderapp.core.domain.models.User


object SampleData{

    val user = User(id = 1, name = "User", username = "username.x", email = "example@gmail.com")

    val post = Post("post body",1,"title",1, username = "username")

    val comment = Comment("body text","sample@gmail.com",1,"name x",1)

    val postComments = PostComments(post, comments = listOf(comment, comment, comment))

}