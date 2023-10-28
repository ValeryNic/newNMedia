package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.mto.addCount

typealias OnLikeListener=(post:Post) -> Unit
typealias OnRepostListener=(post:Post) -> Unit
class PostAdapter(private val onLikeListener: OnLikeListener,private val onRepostListener: OnRepostListener): ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent:ViewGroup,viewType:Int): PostViewHolder {
        val binding=CardPostBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostViewHolder(binding,onLikeListener,onRepostListener)
    }
    override fun onBindViewHolder(holder: PostViewHolder, position: Int){
        val post=getItem(position)
        holder.bind(post)
    }

}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onLikeListener: OnLikeListener,
    private val onRepostListener:OnRepostListener
): RecyclerView.ViewHolder(binding.root){
    fun bind(post:Post){
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            like.setImageResource(
                if (post.likedByMe) R.drawable.ic_like_red_48 else R.drawable.ic_like_white_48
            )
            like.setOnClickListener {
                onLikeListener(post)
            }
            repost.setOnClickListener{
                onRepostListener(post)
            }
            likeCount.text= addCount(post.countLikes)
            repostCount.text= addCount(post.countRepost)
        }
    }
}
class PostDiffCallback:DiffUtil.ItemCallback<Post>(){
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem==newItem
    }
}