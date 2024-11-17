package com.pz.kebapp.data.models

data class Comment(
    val id: Int,
    val text: String,
    val authorName: String,
    val authorId: Int
)