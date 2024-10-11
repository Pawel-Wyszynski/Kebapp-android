package com.pz.kebapp.data.models

data class LoginResponse(
    val accessToken: String,
    val tokenType: String,
    val expiresIn: Int
)
