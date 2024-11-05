package com.pz.kebapp.data

import com.pz.kebapp.data.models.Data
import com.pz.kebapp.data.models.KebabsList
import com.pz.kebapp.data.models.LoginRequest
import com.pz.kebapp.data.models.LoginResponse
import com.pz.kebapp.data.models.RegisterRequest
import com.pz.kebapp.data.models.SendMessageRequest
import com.pz.kebapp.data.models.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("register")
    fun register(@Body request: RegisterRequest): Call<LoginResponse>

    @GET("me")
    suspend fun getUser(
        @Header("Authorization") token: String
    ): Response<User>

    @POST("admin-messages")
    fun sendMessage(
        @Header("Authorization") token: String,
        @Body request: SendMessageRequest
    ): Call<Unit>

    @GET("kebabs")
    suspend fun getAllKebabs(): Response<List<Data>>

    @GET("kebabs/paginated?")
    suspend fun getKebabs(
        @Query("page") page: Int
    ): Response<KebabsList>

    companion object {
        const val BASE_URL = "https://kebapp.wheelwallet.cloud/api/"
    }
}