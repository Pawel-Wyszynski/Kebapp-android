package com.pz.kebapp.functions.userFunctions

import android.content.Context
import android.util.Log
import androidx.navigation.NavHostController
import com.pz.kebapp.data.ApiClient
import com.pz.kebapp.data.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun deleteCommentFunction(
    id: Int,
    commentId: Int,
    context: Context,
    navHostController: NavHostController
) {
    val apiClient = ApiClient()
    val sessionManager = SessionManager(context)

    apiClient.getApiService().deleteComment(
        token = "Bearer ${sessionManager.fetchAuthToken()}",
        id = commentId
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
                    navHostController.navigate("details/$id")
                } else {
                    Log.d("Error", "Usunięcie komentarza nie powiodło się")
                }
            }
        })
}