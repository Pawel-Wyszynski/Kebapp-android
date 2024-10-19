package com.pz.kebapp.pagination

import retrofit2.Response

class PaginationFactory<Page, Kebab>(
    private val initialPage: Page,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextPage: Page) -> Response<Kebab>,
    private inline val getNextPage: suspend (Kebab) -> Page,
    private inline val onError: suspend (Throwable?) -> Unit,
    private inline val onSuccess: suspend (items: Kebab, newPage: Page) -> Unit
) : Pagination<Page, Kebab> {
    private var currentPage = initialPage
    private var isMakingRequest = false

    override suspend fun loadNextPage() {
        if (isMakingRequest) {
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        try {
            val response = onRequest(currentPage)
            if (response.isSuccessful) {
                val items = response.body()!!
                onLoadUpdated(false)
                currentPage = getNextPage(items)!!
                onSuccess(items, currentPage)
                isMakingRequest = false
            } else {
                throw Exception(response.message())
            }
        } catch (e: Exception) {
            onError(e)
            onLoadUpdated(false)
            isMakingRequest = false
        }
    }

    override fun reset() {
        currentPage = initialPage
    }
}