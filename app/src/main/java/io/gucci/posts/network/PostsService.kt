package io.gucci.posts.network

import io.gucci.posts.dto.Post
import retrofit2.Call
import retrofit2.http.GET

interface PostsService {

    @GET("posts")
    fun getPost() : Call<Post>
}