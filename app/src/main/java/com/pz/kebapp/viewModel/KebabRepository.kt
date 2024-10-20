package com.pz.kebapp.viewModel

import com.pz.kebapp.data.ApiClient
import com.pz.kebapp.data.models.KebabsList
import retrofit2.Response

class KebabRepository {
    suspend fun getKebabsList(page: Int): Response<KebabsList> {
        val apiClient = ApiClient()

        return apiClient.getApiService().getKebabs(page)
    }
}