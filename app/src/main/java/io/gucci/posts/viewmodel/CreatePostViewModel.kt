package io.gucci.posts.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.gucci.posts.dto.ResponseSetPost
import io.gucci.posts.dto.SetPost
import io.gucci.posts.network.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

sealed interface AddState {
    object Done : AddState
    object Loading : AddState
    class Error(val message: String) : AddState
}

class CreatePostViewModel(application: Application) : AndroidViewModel(application) {

    val liveData = MutableLiveData<AddState>(AddState.Loading)
    private val postsService = RetrofitFactory.postsService

    fun setPost(post: SetPost) {
        liveData.value = AddState.Loading

        postsService.setPost(post).enqueue(
            object : Callback<ResponseSetPost> { override fun onResponse(call: Call<ResponseSetPost>, response: Response<ResponseSetPost>) {
                liveData.value = AddState.Done
                Toast.makeText(getApplication(), "nav", Toast.LENGTH_SHORT).show()
                if (response.isSuccessful) {
                    Toast.makeText(getApplication(), "${response.body()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseSetPost>, t: Throwable) {
                liveData.value = AddState.Error(t.message.toString())
            }
        })
    }
}

