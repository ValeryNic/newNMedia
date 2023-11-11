package ru.netology.nmedia.dto

import java.net.URL

data class Post(
        var id: Long,
        val author: String,
        val published: String,
        val content: String,
        var likedByMe: Boolean,
        var countLikes:Int,
        var countShare:Int,
        var videoByMe:Boolean,
        var videoURL: URL
)
