package ru.netology.nmedia.dto

data class Post(
        var id: Long,
        val author: String,
        val published: String,
        val content: String,
        var likedByMe: Boolean,
        var countLikes:Int,
        var countShare:Int,
        var videoByMe:Boolean
)
