package io.gucci.posts.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import io.gucci.posts.R
import io.gucci.posts.adapters.PostAdapter
import io.gucci.posts.databinding.FragmentPostsBinding
import io.gucci.posts.viewmodel.PostViewModel
import io.gucci.posts.viewmodel.PostsState

class PostsFragment: Fragment(R.layout.fragment_posts) {
    private lateinit var binding: FragmentPostsBinding

    private val viewModel: PostViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentPostsBinding.bind(view)

        binding.createPost.setOnClickListener {
            findNavController().navigate(R.id.from_posts_to_create)
        }

        binding.recucler.layoutManager = LinearLayoutManager(requireContext())

        viewModel.fetch()

        viewModel.liveData.observe(viewLifecycleOwner) { state ->
            when(state) {
                is PostsState.Success -> {
                    binding.recucler.adapter = PostAdapter(state.posts)
                }
                is PostsState.Error -> {
                    Toast.makeText(requireContext(), "Error: ${state.message}" , Toast.LENGTH_SHORT).show()
                }
                is PostsState.Loading -> {
                }
            }
        }
    }
}