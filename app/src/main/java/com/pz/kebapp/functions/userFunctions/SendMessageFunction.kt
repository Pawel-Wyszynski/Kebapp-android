package com.pz.kebapp.functions.userFunctions

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavHostController
import com.pz.kebapp.data.ApiClient
import com.pz.kebapp.data.SessionManager
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
    val sessionManager = SessionManager(context)

    apiClient.getApiService().sendMessage(
        token = "Bearer ${sessionManager.fetchAuthToken()}",
        SendMessageRequest(
            text = message
        )
    )
        .enqueue(object : Callback<Unit> {
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.d("Error", t.message.toString())
            }

            override fun onResponse(
                call: Call<Unit>,
                response: Response<Unit>
            ) {
                if (response.isSuccessful) {
                    navHostController.navigate("contactus")
                    Toast.makeText(context, "Wiadomość wysłana", Toast.LENGTH_SHORT).show()
                } else {
                    Log.d("Error", "Wysyłka wiadomości nie powiodła się")
                    Toast.makeText(context, "Coś poszło nie tak", Toast.LENGTH_SHORT).show()
                }
            }
        })
}