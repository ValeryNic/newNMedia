package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.ViewModel.PostViewModel
import ru.netology.nmedia.mto.addNumberFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ViewModel: PostViewModel by viewModels<PostViewModel>()
        ViewModel.data.observe(this){
            post ->
            with(binding){
                author.text=post.author
                published.text=post.published
                content.text=post.content
                likeCount.text= addNumberFormat( post.countLikes)
                repostCount.text= addNumberFormat( post.countRepost)
                like.setImageResource(if(post.likedByMe) R.drawable.ic_like_red_48 else R.drawable.ic_like_white_48)

            }
        }



        binding.like.setOnClickListener {
            ViewModel.like()
            println("like1")
        }
        binding.repost.setOnClickListener{
            ViewModel.repost()
            println("repost1")
        }

        println("onCreate $this")



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

