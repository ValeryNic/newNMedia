package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.mto.addLike
import ru.netology.nmedia.dto.Post


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var post = Post(
            id=1,
            author = "Нетология. Университет интернет-профессий.",
            published =  "21 мая в 18.36",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            likedByMe = false,
            countLikes = 8888,
            countRepost =0
        )


        with(binding){
            author.text=post.author
            published.text=post.published
            content.text=post.content

            likeCount.text=post.countLikes.toString()
            repostCount.text=post.countRepost.toString()

            if(post.likedByMe){
                like?.setImageResource(R.drawable.ic_like_red_48)
            }

            like?.setOnClickListener {
                println("click on Like")
                post.likedByMe=!post.likedByMe
                if(post.likedByMe) post.countLikes++ else post.countLikes--
                like.setImageResource( if(post.likedByMe) R.drawable.ic_like_red_48 else R.drawable.ic_like_white_48)
                likeCount.text= addLike(post.countLikes)
            }
            repost?.setOnClickListener {
                repostCount.text=post.countRepost++.toString()
            }
        }



    }


}

