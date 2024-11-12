package com.pz.kebapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pz.kebapp.data.models.Data
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {
    private val kebabRepository = KebabRepository()
    var state by mutableStateOf(DetailsState())
    var selectedKebab by mutableStateOf<Data?>(null)

    init {
        loadKebabs()
    }

    private fun loadKebabs() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            try {
                val response = kebabRepository.getAllKebabsList()
                state = if (response.isSuccessful && response.body() != null) {
                    state.copy(
                        kebabs = response.body()!!,
                        isLoading = false
                    )
                } else {
                    state.copy(
                        error = response.message(),
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                state = state.copy(
                    error = e.message ?: "Unknown error",
                    isLoading = false
                )
            }
        }
    }

    fun getKebab(id: Int) {
        selectedKebab = state.kebabs.find { it.id == id }
        println("Wybrany kebab o ID $id: $selectedKebab")
    }
}

data class DetailsState(
    val kebabs: List<Data> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
