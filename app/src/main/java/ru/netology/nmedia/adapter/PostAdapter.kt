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
    fun share(post: Post)
    fun video(post: Post)
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

            //like.setText(addCount( if(post.likedByMe)  post.countLikes+1 else post.countLikes-1))
            onLike.isChecked=post.likedByMe
            onLike.text= addCount( post.countLikes+1)
            //like.text=addCount( if(post.likedByMe)  post.countLikes+1 else post.countLikes-1)
            onShare.text= addCount(post.countShare+1)

            onLike.setOnClickListener{
                onInterationListener.like(post)
            }
            onShare.setOnClickListener{
                if(post.videoByMe) {
                    onInterationListener.share(post)
                }
            }
            onVideoButton.setOnClickListener{
                onInterationListener.video(post)
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