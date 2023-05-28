package io.gucci.posts.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso
import io.gucci.posts.R
import io.gucci.posts.dto.Post

class PostsHolder(view: View) : ViewHolder(view)

class PostAdapter(val listPost: Post): Adapter<PostsHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.posts_card, parent, false)

        return PostsHolder(view)
    }

    override fun getItemCount(): Int {
        return listPost.post.id
    }

    override fun onBindViewHolder(holder: PostsHolder, position: Int) {
        val image: RoundedImageView = holder.itemView.findViewById(R.id.userImage)
        val name: TextView = holder.itemView.findViewById(R.id.username)
        val content: TextView = holder.itemView.findViewById(R.id.message)
        val like: TextView = holder.itemView.findViewById(R.id.like)
        val comment: TextView = holder.itemView.findViewById(R.id.comment)
        val share: TextView = holder.itemView.findViewById(R.id.share)

        Picasso.get().load(listPost.post.author.avatar).into(image)
        name.text = listPost.post.author.name
        content.text = listPost.post.content
        like.text = listPost.post.likes.toString()
        comment.text = listPost.post.comments.toString()
        share.text = listPost.post.shares.toString()
    }
}