package ru.netology.nmedia.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.netology.nmedia.dto.Post

class PostRepositoryFileImpl(
    private val context: Context,
) : PostRepository {
    private val gson = Gson()
    private val type = TypeToken.getParameterized(List::class.java, Post::class.java).type
    private val filePostName = "posts.json"

    private var nextId = 1L
        set(value) {
            field = value
        }
    private val fileNextIdName = "next_id.json"

    private var posts = emptyList<Post>()
        set(value) {
            field = value
            data.value = value
            sync()
        }

    private val data = MutableLiveData(posts)

    init {
        val fileNextId = context.filesDir.resolve(fileNextIdName)
        if (fileNextId.exists()) {
            context.openFileInput(fileNextIdName).bufferedReader().use {
                nextId = gson.fromJson(it, Long::class.java)
            }
        } else {
            nextId = 1L
        }

        val filePost = context.filesDir.resolve(filePostName)
        if (filePost.exists()) {
            context.openFileInput(filePostName).bufferedReader().use {
                posts = gson.fromJson(it, type)
            }
        } else {
            posts = emptyList()
        }
    }



    override fun getAll(): LiveData<List<Post>> = data

    override fun save(post: Post) {
        if (post.id == 0L) {
            posts = listOf(
                post.copy(
                    id = nextId++,
                    author = "Me",
                    likedByMe = false,
                    published = "now"
                )
            ) + posts
        }

        posts = posts.map {
            if (it.id != post.id) it else it.copy(content = post.content)
        }
        sync()
    }

    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(
                likedByMe = !it.likedByMe,
                countLikes = if (it.likedByMe) (it.countLikes - 1) else (it.countLikes + 1)
            )
        }
    }

    override fun shareById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(countShare = it.countShare + 1)
        }
    }

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
    }

    private fun sync() {
        val filePostName="posts.json"
        context.openFileOutput(filePostName, Context.MODE_PRIVATE).bufferedWriter().use {
            it.write(gson.toJson(posts))
        }
        context.filesDir.resolve(filePostName).writer().buffered().use {
            it.write(gson.toJson(posts))
        }
        val fileNextIdName="next_id.json"
        context.openFileOutput(fileNextIdName, Context.MODE_PRIVATE).bufferedWriter().use {
            it.write(gson.toJson(posts))
        }
        context.filesDir.resolve(fileNextIdName).writer().buffered().use {
            it.write(gson.toJson(nextId))
        }
    }
}