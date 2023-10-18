package ru.netology.nmedia.dto

data class Post(
        val id: Int,
        val author: String,
        val published: String,
        val content: String,
        val likedByMe: Boolean,
        val countLikes:Int,
        val countRepost:Int,
        val repostAdd: Boolean
)
