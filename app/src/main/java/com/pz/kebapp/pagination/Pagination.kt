package com.pz.kebapp.pagination

interface Pagination<Page, Kebab> {
    suspend fun loadNextPage()
    fun reset()
}