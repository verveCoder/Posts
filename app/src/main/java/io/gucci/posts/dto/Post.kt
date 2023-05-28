package io.gucci.posts.dto

data class Post(
    val message: Any,
    val post: PostX
)

class PostList: ArrayList<Post>()