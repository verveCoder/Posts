package io.gucci.posts.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import io.gucci.posts.R
import io.gucci.posts.databinding.FragmentCreatePostBinding
import io.gucci.posts.dto.SetPost
import io.gucci.posts.viewmodel.AddState
import io.gucci.posts.viewmodel.CreatePostViewModel

class CreatePostFragment: Fragment(R.layout.fragment_create_post) {
    private lateinit var binding: FragmentCreatePostBinding
    private val viewModel : CreatePostViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentCreatePostBinding.bind(view)

        binding.create.setOnClickListener {
            val name : String = binding.Author.toString()
            val text : String = binding.newPostMessage.toString()
            if (name.isNotEmpty() && text.isNotEmpty()){
                val post = SetPost(name,text)
                viewModel.setPost(post)
            }
        }

        viewModel.liveData.observe(viewLifecycleOwner) {state ->
            when (state){
                is AddState.Done -> {
                    findNavController().popBackStack()
                }
                is AddState.Error -> {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                }
                is AddState.Loading -> {}
            }
        }
    }
}