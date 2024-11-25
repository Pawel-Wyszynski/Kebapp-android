package com.pz.kebapp.viewModel

import com.pz.kebapp.data.ApiClient
import com.pz.kebapp.data.models.Data
import com.pz.kebapp.data.models.KebabsList
import retrofit2.Response

class KebabRepository {
    suspend fun getKebabsList(
        page: Int,
        sortBy: String? = null,
        isAscending: Boolean
    ): Response<KebabsList> {
        val apiClient = ApiClient()

        return if (isAscending) {
            apiClient.getApiService().getKebabs(page = page, orderByAsc = sortBy)
        } else {
            apiClient.getApiService().getKebabs(page = page, orderByDesc = sortBy)
        }
    }

    suspend fun getAllKebabsList(): Response<List<Data>> {
        val apiClient = ApiClient()

        return apiClient.getApiService().getAllKebabs()
    }
}