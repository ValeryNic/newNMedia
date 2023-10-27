package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post
class PostRepositoryInMemoryImpl: PostRepository {

    private var posts = listOf(
        Post(
        id=1,
        author = "Нетология. Университет интернет-профессий.",
        published =  "21 мая в 18.36",
        content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
        likedByMe = false,
        countLikes = 4560,
        countRepost =0
        ),
        Post(
            id=2,
            author = "Нетология. Университет интернет-профессий.",
            published =  "21 мая в 18.36",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            likedByMe = false,
            countLikes = 999,
            countRepost =0
        ),
        Post(
            id=3,
            author = "Нетология. Университет интернет-профессий.",
            published =  "21 мая в 18.36",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            likedByMe = false,
            countLikes = 888,
            countRepost =0
        )
    )
    private val data=MutableLiveData(posts)
    override fun getAll(): LiveData<List<Post>> = data

    override fun likeById(id:Long) {
            posts = posts.map {
                if (it.id != id) it else it.copy(likedByMe = !it.likedByMe, countLikes = if(it.likedByMe) it.countLikes+1 else it.countLikes-1)
            }
        data.value = posts
        }

    override fun repostById(id:Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(countRepost = it.countRepost + 1)
        }
        data.value = posts
    }
}