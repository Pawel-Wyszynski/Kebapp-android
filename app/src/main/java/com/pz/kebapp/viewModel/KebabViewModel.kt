package com.pz.kebapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pz.kebapp.data.models.KebabsListItem
import kotlinx.coroutines.launch

class KebabViewModel : ViewModel() {
    private val kebabRepository = KebabRepository()
    var state by mutableStateOf(ScreenState())

    init {
        viewModelScope.launch {
            val response = kebabRepository.getKebabsList()
            state = state.copy(
                kebabs = response.body()!!
            )
        }
    }
}

data class ScreenState(
    val kebabs: List<KebabsListItem> = emptyList()
)