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

    init {
        viewModelScope.launch {
            val response = kebabRepository.getAllKebabsList()
            state = state.copy(
                kebabs = response.body()!!
            )
        }
    }
}

data class DetailsState(
    val kebabs: List<Data> = emptyList()
)