package ru.netology.nmedia.dto

data class Post(
        val id: Int,
        val author: String,
        val published: String,
        val content: String,
        var likedByMe: Boolean,
        var countLikes:Int,
        var countRepost:Int
)
