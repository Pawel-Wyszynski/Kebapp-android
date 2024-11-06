package com.pz.kebapp.viewModel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pz.kebapp.data.models.Data
import kotlinx.coroutines.launch

class UserViewModel(context: Context) : ViewModel() {
    private val userRepository = UserRepository(context)
    var state by mutableStateOf(UserState())

    init {
        viewModelScope.launch {
            val response = userRepository.getFavoritesKebabsList()
            response.body()?.let { user ->
                state = state.copy(
                    user = user.likedKebabs
                )
            }
        }
    }
}

data class UserState(
    val user: List<Data> = emptyList()
)