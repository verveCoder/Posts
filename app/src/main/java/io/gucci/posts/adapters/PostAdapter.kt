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

    override fun getItemCount(): Int = listPost.post.size

    override fun onBindViewHolder(holder: PostsHolder, position: Int) {
        val list = listPost.post[position]
        val image: RoundedImageView = holder.itemView.findViewById(R.id.userImage)
        val name: TextView = holder.itemView.findViewById(R.id.username)
        val content: TextView = holder.itemView.findViewById(R.id.message)
        val like: TextView = holder.itemView.findViewById(R.id.like)
        val comment: TextView = holder.itemView.findViewById(R.id.comment)
        val share: TextView = holder.itemView.findViewById(R.id.share)

        Picasso.get().load(list.author.avatar).into(image)
        name.text = list.author.name
        content.text = list.content
        like.text = list.likes.toString()
        comment.text = list.comments.toString()
        share.text = list.shares.toString()
    }
}