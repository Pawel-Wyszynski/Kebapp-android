package com.pz.kebapp.functions.userFunctions

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavHostController
import com.pz.kebapp.data.ApiClient
import com.pz.kebapp.data.models.SendMessageRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun sendMessageFunction(
    message: String,
    context: Context,
    navHostController: NavHostController
) {
    val apiClient = ApiClient()

    apiClient.getApiService(context).sendMessage(
        SendMessageRequest(
            text = message
        )
    )
        .enqueue(object : Callback<Unit> {
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.d("Error", t.message.toString())
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<Unit>,
                response: Response<Unit>
            ) {
                if (response.isSuccessful) {
                    navHostController.navigate("contactus")
                } else {
                    Log.d("Error", "Wysyłka wiadomości nie powiodła się")
                    Toast.makeText(context, "Błędne dane", Toast.LENGTH_SHORT).show()
                }
            }
        })
}