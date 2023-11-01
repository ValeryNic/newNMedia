package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post
class PostRepositoryInMemoryImpl: PostRepository {
    private var nextId:Long=0
    private var posts = listOf(
        Post(
        id=nextId++,
        author = "Нетология. Университет интернет-профессий.",
        published =  "21 мая в 18.36",
        content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
        likedByMe = false,
        countLikes = 4560,
        countRepost =0
        ),
        Post(
            id=nextId++,
            author = "Нетология. Университет интернет-профессий.",
            published =  "21 мая в 18.36",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            likedByMe = false,
            countLikes = 999,
            countRepost =0
        ),
        Post(
            id=nextId++,
            author = "Нетология. Университет интернет-профессий.",
            published =  "21 мая в 18.36",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            likedByMe = false,
            countLikes = 888,
            countRepost =0
        ),
    ).reversed()

    private val data=MutableLiveData(posts)
    override fun getAll(): LiveData<List<Post>> = data

    override fun likeById(id:Long) {
            posts = posts.map {
                if (it.id != id) it else it.copy(likedByMe = !it.likedByMe, countLikes = it.countLikes+1)
            }
        data.value = posts
        }

    override fun repostById(id:Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(countRepost = it.countRepost + 1)
        }
        data.value = posts
    }

    override fun removeById(id: Long) {
        posts=posts.filter { it.id !=id }
        data.value=posts
    }

    override fun save(post: Post) {
        posts = if(post.id==0L){
            listOf(
                post.copy(
                    id=nextId++,
                    author = "Me",
                    published = "now",
                    likedByMe = false,
                    countLikes = 0,
                    countRepost = 0
                )
            ) + posts
        } else {
            posts.map{ if(it.id!=post.id) it else it.copy(content=post.content)}
        }
        data.value=posts
        return
    }
}