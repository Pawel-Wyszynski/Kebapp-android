package com.pz.kebapp.data

import com.pz.kebapp.data.models.KebabsList
import com.pz.kebapp.data.models.LoginRequest
import com.pz.kebapp.data.models.LoginResponse
import com.pz.kebapp.data.models.RegisterRequest
import com.pz.kebapp.data.models.SendMessageRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("register")
    fun register(@Body request: RegisterRequest): Call<LoginResponse>

    @POST("admin-messages")
    fun sendMessage(
        @Header("Authorization") token: String,
        @Body request: SendMessageRequest
    ): Call<Unit>

    @GET("kebabs")
    suspend fun getAllKebabs(): Response<KebabsList>

    companion object {
        const val BASE_URL = "https://kebapp.wheelwallet.cloud/api/"
    }
}