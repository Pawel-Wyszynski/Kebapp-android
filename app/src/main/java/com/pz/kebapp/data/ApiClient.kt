package com.pz.kebapp.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    private lateinit var apiService: ApiService

    fun getApiService(): ApiService {
        if (!::apiService.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpClient)
                .build()

            apiService = retrofit.create(ApiService::class.java)
        }
        return apiService
    }

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okhttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()
}