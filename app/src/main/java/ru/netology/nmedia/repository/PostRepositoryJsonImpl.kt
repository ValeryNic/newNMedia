package ru.netology.nmedia.repository
import android.content.Context
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URL


//class PostRepositoryInMemoryImpl: PostRepository {
class PostRepositoryJsonImpl(context: Context,):PostRepository{
    private val gson = Gson()
    private val prefs = context.getSharedPreferences("repo", Context.MODE_PRIVATE)
    private val type = TypeToken.getParameterized(List::class.java, Post::class.java).type
    private val postKey = "posts"
    private val nextIdKey = "next_id"
    private var nextId:Long=0

    private var posts = listOf(
        Post(
        id=nextId++,
        author = "Нетология. Университет интернет-профессий.",
        published =  "21 мая в 18.36",
        content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
        likedByMe = false,
        countLikes = 4560,
        countShare =0,
        videoByMe = false,
<<<<<<< HEAD
        videoURL= URL("https://www.youtube.com/watch?v=WhWc3b3KhnY")
=======
        videoURL = URL("https://www.youtube.com/watch?v=WhWc3b3KhnY")
>>>>>>> json
        ),
        Post(
            id=nextId++,
            author = "Нетология. Университет интернет-профессий.",
            published =  "21 мая в 18.36",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            likedByMe = false,
            countLikes = 999,
            countShare =0,
<<<<<<< HEAD
            videoByMe =false,
            videoURL=URL("https://www.youtube.com/watch?v=WhWc3b3KhnY")
=======
            videoByMe = false,
            videoURL = URL("https://www.youtube.com/watch?v=WhWc3b3KhnY")
>>>>>>> json
        ),
        Post(
            id=nextId++,
            author = "Нетология. Университет интернет-профессий.",
            published =  "21 мая в 18.36",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            likedByMe = false,
            countLikes = 888,
            countShare =0,
            videoByMe = false,
<<<<<<< HEAD
            videoURL=URL("https://www.youtube.com/watch?v=WhWc3b3KhnY")
=======
            videoURL = URL("https://www.youtube.com/watch?v=WhWc3b3KhnY")
>>>>>>> json
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Знаний хватит на всех: на следующей неделе разбираемся с разработкой мобильных приложений, учимся рассказывать истории и составлять PR-стратегию прямо на бесплатных занятиях \uD83D\uDC47",
            published = "18 сентября в 10:12",
            likedByMe = false,
            countLikes = 888,
            countShare =0,
            videoByMe = false,
<<<<<<< HEAD
            videoURL=URL("https://www.youtube.com/watch?v=WhWc3b3KhnY")
=======
            videoURL = URL("https://www.youtube.com/watch?v=WhWc3b3KhnY")
>>>>>>> json
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Языков программирования много, и выбрать какой-то один бывает нелегко. Собрали подборку статей, которая поможет вам начать, если вы остановили свой выбор на JavaScript.",
            published = "19 сентября в 10:24",
            likedByMe = false,
            countLikes = 888,
            countShare =0,
            videoByMe = false,
<<<<<<< HEAD
            videoURL=URL("https://www.youtube.com/watch?v=WhWc3b3KhnY")
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Большая афиша мероприятий осени: конференции, выставки и хакатоны для жителей Москвы, Ульяновска и Новосибирска \uD83D\uDE09",
            published = "19 сентября в 14:12",
            likedByMe = false,
            countLikes = 888,
            countShare =0,
            videoByMe = false,
            videoURL=URL("https://www.youtube.com/watch?v=WhWc3b3KhnY")
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Диджитал давно стал частью нашей жизни: мы общаемся в социальных сетях и мессенджерах, заказываем еду, такси и оплачиваем счета через приложения.",
            published = "20 сентября в 10:14",
            likedByMe = false,
            countLikes = 888,
            countShare =0,
            videoByMe = false,
            videoURL=URL("https://www.youtube.com/watch?v=WhWc3b3KhnY")
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "\uD83D\uDE80 24 сентября стартует новый поток бесплатного курса «Диджитал-старт: первый шаг к востребованной профессии» — за две недели вы попробуете себя в разных профессиях и определите, что подходит именно вам → http://netolo.gy/fQ",
            published = "21 сентября в 10:12",
            likedByMe = false,
            countLikes = 888,
            countShare =0,
            videoByMe = false,
            videoURL=URL("https://www.youtube.com/watch?v=WhWc3b3KhnY")
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Таймбоксинг — отличный способ навести порядок в своём календаре и разобраться с делами, которые долго откладывали на потом. Его главный принцип — на каждое дело заранее выделяется определённый отрезок времени. В это время вы работаете только над одной задачей, не переключаясь на другие. Собрали советы, которые помогут внедрить таймбоксинг \uD83D\uDC47\uD83C\uDFFB",
            published = "22 сентября в 10:12",
            likedByMe = false,
            countLikes = 888,
            countShare =0,
            videoByMe = false,
            videoURL=URL("https://www.youtube.com/watch?v=WhWc3b3KhnY")
=======
            videoURL = URL("https://www.youtube.com/watch?v=WhWc3b3KhnY")
>>>>>>> json
        ),
    ).reversed()
        set(value) {
            field = value
            data.value = value
            sync()
        }
    private val data=MutableLiveData(posts)
    init {
        prefs.getString(postKey, null)?.let {
            posts = gson.fromJson(it, type)

        }
        nextId = prefs.getLong(nextIdKey, nextId)
        data.value = posts
    }




    override fun getAll(): LiveData<List<Post>> = data

    override fun likeById(id:Long) {
            posts = posts.map {
                if (it.id != id) it else it.copy(likedByMe = !it.likedByMe, countLikes = if(it.likedByMe) it.countLikes-1 else it.countLikes+1)

            }
        }

    override fun shareById(id:Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(countShare = it.countShare + 1)
        }

    }
    override fun videoById(id: Long){}

    override fun removeById(id: Long) {
        posts=posts.filter { it.id !=id }

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
                    countShare = 0
                )
            ) + posts
        } else {
            posts.map{ if(it.id!=post.id) it else it.copy(content=post.content)}
        }

        return
    }
    private fun sync() {
        with(prefs.edit()) {
            putString(postKey, gson.toJson(posts))
            putLong(nextIdKey, nextId)
            apply()
        }
    }
}