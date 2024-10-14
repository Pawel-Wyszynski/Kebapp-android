package com.pz.kebapp.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pz.kebapp.data.ApiClient
import com.pz.kebapp.data.models.KebabsList
import com.pz.kebapp.data.models.KebabsListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KebabViewModel : ViewModel() {
    private val _kebabsData: MutableStateFlow<List<KebabsListItem>> = MutableStateFlow(listOf())
    val kebabsData: StateFlow<List<KebabsListItem>> = _kebabsData

    fun getKebabs(context: Context) {
        val apiClient = ApiClient()

        val call: Call<KebabsList> =
            apiClient.getApiService(context).getAllKebabs()

        call.enqueue(object : Callback<KebabsList> {
            override fun onFailure(call: Call<KebabsList>, t: Throwable) {
                Log.d("Failed Retrieve", t.message.toString())
            }

            override fun onResponse(
                call: Call<KebabsList>,
                response: Response<KebabsList>
            ) {
                if (response.isSuccessful) {
                    val responseData: List<KebabsListItem>? = response.body()
                    if (responseData != null) {
                        viewModelScope.launch {
                            _kebabsData.value = responseData.filter { true }
                        }
                    }
                }
            }
        })
    }
}