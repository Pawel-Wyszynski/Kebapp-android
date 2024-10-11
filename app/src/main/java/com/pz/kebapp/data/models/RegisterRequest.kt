package com.pz.kebapp.data.models

data class RegisterRequest(
    val email: String,
    val password: String,
    val password_confirmation: String,
    val name: String
)
