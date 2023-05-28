package io.gucci.posts.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import io.gucci.posts.R
import io.gucci.posts.databinding.FragmentCreatePostBinding

class CreatePostFragment: Fragment(R.layout.fragment_create_post) {
    private lateinit var binding: FragmentCreatePostBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentCreatePostBinding.bind(view)
    }
}