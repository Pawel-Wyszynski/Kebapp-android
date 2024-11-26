package com.pz.kebapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pz.kebapp.data.models.Data
import com.pz.kebapp.pagination.PaginationFactory
import kotlinx.coroutines.launch

class KebabViewModel : ViewModel() {
    private val kebabRepository = KebabRepository()
    var state by mutableStateOf(ScreenState())

    private val pagination = PaginationFactory(
        initialPage = state.page,
        onLoadUpdated = {
            state = state.copy(
                isLoading = it
            )
        },
        onRequest = { nextPage ->
            kebabRepository.getKebabsList(
                nextPage,
                state.sortBy,
                state.isAscending,
                state.filters,
                state.orderFilters,
                state.meatFilters,
                state.sauceFilters,
                state.statusFilters
            )
        },
        getNextPage = {
            state.page + 1
        },
        onError = {
            state = state.copy(error = it?.localizedMessage)
        },
        onSuccess = { items, newPage ->
            state = state.copy(
                kebabs = state.kebabs + items.data,
                page = newPage,
                endReached = state.page == items.meta.last_page
            )
        }
    )

    init {
        loadNextKebabs()
    }

    fun loadNextKebabs() {
        if (state.endReached) return

        viewModelScope.launch {
            pagination.loadNextPage()
        }
    }

    fun applySort(
        selectedSort: String?,
        isAscending: Boolean,
        filters: Map<String, Boolean>,
        orderFilters: Map<String, Boolean>,
        meatFilters: List<String>,
        sauceFilters: List<String>,
        statusFilters: List<String>
    ) {
        val cleanedMeatFilters = meatFilters.takeIf { it.isNotEmpty() }
        val cleanedSauceFilters = sauceFilters.takeIf { it.isNotEmpty() }
        val cleanedStatusFilters = statusFilters.takeIf { it.isNotEmpty() }

        viewModelScope.launch {
            state = state.copy(
                kebabs = emptyList(),
                page = 1,
                sortBy = selectedSort,
                isAscending = isAscending,
                filters = filters,
                orderFilters = orderFilters,
                meatFilters = cleanedMeatFilters ?: emptyList(),
                sauceFilters = cleanedSauceFilters ?: emptyList(),
                statusFilters = cleanedStatusFilters ?: emptyList()
            )

            pagination.reset()
            pagination.loadNextPage()
        }
    }
}

data class ScreenState(
    val kebabs: List<Data> = emptyList(),
    val page: Int = 1,
    val endReached: Boolean = false,
    val error: String? = null,
    val isLoading: Boolean = false,
    val sortBy: String? = null,
    val isAscending: Boolean = true,
    val filters: Map<String, Boolean> = emptyMap(),
    val orderFilters: Map<String, Boolean> = emptyMap(),
    val meatFilters: List<String> = emptyList(),
    val sauceFilters: List<String> = emptyList(),
    val statusFilters: List<String> = emptyList()
)