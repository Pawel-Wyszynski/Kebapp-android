package com.pz.kebapp.viewModel

import com.pz.kebapp.data.ApiClient
import com.pz.kebapp.data.models.Data
import com.pz.kebapp.data.models.KebabsList
import retrofit2.Response

class KebabRepository {
    suspend fun getKebabsList(
        page: Int,
        sortBy: String? = null,
        isAscending: Boolean,
        filters: Map<String, Boolean>? = null,
        meatFilters: List<String>? = null,
        sauceFilters: List<String>? = null,
        statusFilters: List<String>? = null
    ): Response<KebabsList> {
        val apiClient = ApiClient()

        val isOpenNow = filters?.get("isOpenNow")?.takeIf { it }
        val isCraft = filters?.get("isCraft")?.takeIf { it }
        val isChainStore = filters?.get("isChainStore")?.takeIf { it }
        val isFoodTruck = filters?.get("isFoodTruck")?.takeIf { it }
        val sauces = sauceFilters?.takeIf { it.isNotEmpty() }?.joinToString(",")
        val meats = meatFilters?.takeIf { it.isNotEmpty() }?.joinToString(",")
        val statuses = statusFilters?.takeIf { it.isNotEmpty() }?.joinToString(",")

        return if (isAscending) {
            apiClient.getApiService().getKebabs(
                page = page,
                orderByAsc = sortBy,
                isOpenNow = isOpenNow,
                isCraft = isCraft,
                isChainStore = isChainStore,
                isFoodTruck = isFoodTruck,
                sauces = sauces,
                meatTypes = meats,
                statuses = statuses
            )
        } else {
            apiClient.getApiService().getKebabs(
                page = page,
                orderByDesc = sortBy,
                isOpenNow = isOpenNow,
                isCraft = isCraft,
                isChainStore = isChainStore,
                isFoodTruck = isFoodTruck,
                sauces = sauces,
                meatTypes = meats,
                statuses = statuses
            )
        }
    }

    suspend fun getAllKebabsList(): Response<List<Data>> {
        val apiClient = ApiClient()

        return apiClient.getApiService().getAllKebabs()
    }
}