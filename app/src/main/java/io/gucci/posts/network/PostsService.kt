package io.gucci.posts.network

import io.gucci.posts.dto.GetPost
import io.gucci.posts.dto.ResponseSetPost
import io.gucci.posts.dto.SetPost
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PostsService {

    @GET("posts")
    fun getPost() : Call<GetPost>

    @POST("posts")
    fun setPost(@Body post: SetPost) : Call<ResponseSetPost>
}