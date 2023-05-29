package io.gucci.posts.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.gucci.posts.dto.GetPost
import io.gucci.posts.network.RetrofitFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed interface PostsState {
    object Loading : PostsState
    class Success(val posts: GetPost) : PostsState
    class Error(val message: String) : PostsState
}

class PostViewModel(application: Application) : AndroidViewModel(application) {
    val liveData = MutableLiveData<PostsState>(PostsState.Loading)
    private val postsService = RetrofitFactory.postsService

    fun fetch() {

        liveData.value = PostsState.Loading

        viewModelScope.launch(Dispatchers.IO) {

            try {
                val response = postsService.getPost().execute()

                viewModelScope.launch(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val request = response.body()
                        Log.d("Request", "onResponse ${request}")
                        liveData.value = request?.let { PostsState.Success(it) }
                    }
                    else {
                        liveData.value = PostsState.Error(response.message())
                    }
                }
            }
            catch (e: java.lang.Exception) {
                Log.d("Create", "createTodo: $e")
                launch(Dispatchers.Main){
                    liveData.value = PostsState.Error(e.message ?: "Error")
                }
            }
        }
    }
}