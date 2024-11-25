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
            kebabRepository.getKebabsList(nextPage, state.sortBy, state.isAscending)
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

    fun applySort(selectedSort: String?, isAscending: Boolean) {
        viewModelScope.launch {
            state = state.copy(
                kebabs = emptyList(),
                page = 1,
                sortBy = selectedSort,
                isAscending = isAscending
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
    val isAscending: Boolean = true
)