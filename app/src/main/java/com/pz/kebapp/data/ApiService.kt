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
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
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

    @POST("like/kebab/{id}")
    fun likeKebab(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Call<Unit>

    @POST("comment/kebab/{id}")
    fun comment(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body request: SendMessageRequest
    ): Call<Unit>

    @DELETE("comment/{id}")
    fun deleteComment(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Call<Unit>

    @GET("kebabs")
    suspend fun getAllKebabs(): Response<List<Data>>

    @GET("kebabs/paginated?")
    suspend fun getKebabs(
        @Query("page") page: Int,
        @Query("orderBy") orderByAsc: String? = null,
        @Query("orderByDesc") orderByDesc: String? = null,
        @Query("isOpenNow") isOpenNow: Boolean? = null,
        @Query("isCraft") isCraft: Boolean? = null,
        @Query("isChainStore") isChainStore: Boolean? = null,
        @Query("isFoodTruck") isFoodTruck: Boolean? = null,
        @Query("hasGlovo") hasGlovo: Boolean? = null,
        @Query("hasPyszne") hasPyszne: Boolean? = null,
        @Query("hasUberEats") hasUberEats: Boolean? = null,
        @Query("hasPhone") hasPhone: Boolean? = null,
        @Query("hasWebsite") hasWebsite: Boolean? = null,
        @Query("sauces") sauces: String? = null,
        @Query("meatTypes") meatTypes: String? = null,
        @Query("statuses") statuses: String? = null
    ): Response<KebabsList>

    companion object {
        const val BASE_URL = "https://kebapp.bity24h.pl/api/"
    }
}