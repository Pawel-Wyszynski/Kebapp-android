package com.pz.kebapp.data.models

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val isAdmin: Boolean,
    val likedKebabs: List<Data>
)