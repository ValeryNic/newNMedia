package ru.netology.nmedia.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemoryImpl
private val empty=Post(
    id=0,
    author = "Me",
    published = "",
    likedByMe = false,
    countRepost = 0,
    content = "",
    countLikes = 0
)

class PostViewModel:ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    val edited = MutableLiveData(empty)
    fun likeById(id:Long)=repository.likeById(id)
    fun repostById(id:Long)=repository.repostById(id)
    fun removeById(id: Long)=repository.removeById(id)
    fun save(){
        edited.value?.let {
        repository.save(it)
        }
        edited.value=empty
    }
    fun chandeContentAndSave(content:String) {
        edited.value?.let {
            val text = content.trim()
            //if (it.content == text) {
            //    return@let
            //}
            //edited.value = it.copy(content = text)
            //---variant---
            if(it.content != text){
                repository.save(it.copy(content=text))
                //edited.value=it.copy(content = text)
            }
        }
        edited.value= empty
    }
    fun edit(post: Post){
        edited.value=post
    }
}