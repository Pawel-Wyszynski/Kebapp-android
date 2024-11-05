package com.pz.kebapp.viewModel

import android.content.Context
import com.pz.kebapp.data.ApiClient
import com.pz.kebapp.data.SessionManager
import com.pz.kebapp.data.models.User
import retrofit2.Response

class UserRepository(context: Context) {
    private val sessionManager = SessionManager(context)
    suspend fun getFavoritesKebabsList(): Response<User> {
        val apiClient = ApiClient()
        val token = sessionManager.fetchAuthToken()

        return apiClient.getApiService().getUser("Bearer $token")
    }
}