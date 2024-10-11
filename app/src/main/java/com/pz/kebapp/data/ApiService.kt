package com.pz.kebapp.data

import com.pz.kebapp.data.models.LoginRequest
import com.pz.kebapp.data.models.LoginResponse
import com.pz.kebapp.data.models.RegisterRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("register")
    fun register(@Body request: RegisterRequest): Call<LoginResponse>

    companion object {
        const val BASE_URL = "https://kebapp.wheelwallet.cloud/api/"
    }
}