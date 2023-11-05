package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.mto.addCount
interface OnInterationListener{
    fun like(post:Post)
    fun remove(post:Post)
    fun edit(post:Post)
    fun repost(post: Post)
}

class PostAdapter(private val onInterationListener: OnInterationListener,


): ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent:ViewGroup,viewType:Int): PostViewHolder {
        val binding=CardPostBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostViewHolder(binding,onInterationListener)
    }
    override fun onBindViewHolder(holder: PostViewHolder, position: Int){
        val post=getItem(position)
        holder.bind(post)
    }

}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onInterationListener: OnInterationListener
):RecyclerView.ViewHolder(binding.root){
    fun bind(post:Post){
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            like.setImageResource(
                if (post.likedByMe) R.drawable.ic_like_red_48 else R.drawable.ic_like_white_48
            )
            like.setOnClickListener {
                onInterationListener.like(post)
            }
            repost.setOnClickListener{
                onInterationListener.repost(post)
            }
            menu.setOnClickListener{
                PopupMenu(it.context,it).apply{
                    inflate(R.menu.menu_options)
                    setOnMenuItemClickListener { item ->
                        when(item.itemId){
                            R.id.remove ->{
                                onInterationListener.remove(post)
                                true
                            }
                            R.id.edit -> {
                                onInterationListener.edit(post)
                                true
                            }
                            else -> false
                        }

                    }
                }.show()
            }
            backup.setOn
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