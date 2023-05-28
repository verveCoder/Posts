package io.gucci.posts.dto

data class PostX(
    val author: Author,
    val comments: Int,
    val content: String,
    val id: Int,
    val likes: Int,
    val shares: Int
)