package com.pz.kebapp.functions.authFunctions

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavHostController
import com.pz.kebapp.data.ApiClient
import com.pz.kebapp.data.SessionManager
import com.pz.kebapp.data.models.LoginResponse
import com.pz.kebapp.data.models.RegisterRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun registerFunction(
    email: String,
    password: String,
    password_confirmation: String,
    name: String,
    context: Context,
    navHostController: NavHostController
) {
    val apiClient = ApiClient()
    val sessionManager = SessionManager(context)

    apiClient.getApiService().register(
        RegisterRequest(
            email = email,
            password = password,
            password_confirmation = password_confirmation,
            name = name,
        )
    )
        .enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("Error", "Błąd rejestracji")
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                val loginResponse = response.body()

                if (loginResponse?.accessToken != null) {
                    sessionManager.saveAuthToken(loginResponse.accessToken)
                    navHostController.navigate("home")
                } else {
                    Log.d("Error", "Błąd rejestracji")
                    Toast.makeText(context, "Błędne dane", Toast.LENGTH_SHORT).show()
                }
            }
        })
}