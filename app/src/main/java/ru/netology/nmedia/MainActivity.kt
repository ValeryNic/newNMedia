package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.ViewModel.PostViewModel
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.mto.addLike


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ViewModel: PostViewModel by viewModels<PostViewModel>()
        ViewModel.data.observe(this) { posts ->//List<Post>
            binding.container.removeAllViews()//закрываем все View
            posts.map { post ->
                CardPostBinding.inflate(layoutInflater, binding.container, false).apply {//true -добавляет содержимое поста сразу после создания View
                    author.text = post.author
                    published.text = post.published
                    content.text = post.content
                    likeCount.text = addLike(post.countLikes)
                    repostCount.text =addLike(post.countRepost)
                    like.setImageResource(if (post.likedByMe) R.drawable.ic_like_red_48 else R.drawable.ic_like_white_48)
                    like.setOnClickListener {
                        ViewModel.likeById(post.id)
                    }
                    repost.setOnClickListener {
                        ViewModel.repostById(post.id)
                    }
                }.root
            }.forEach() {
                binding.container.addView(it)//добавляем настроенное View во ViewActivity
            }
        }
    }


    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}

