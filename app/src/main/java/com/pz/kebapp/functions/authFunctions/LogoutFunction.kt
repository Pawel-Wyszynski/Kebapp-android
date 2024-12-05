package com.pz.kebapp.functions.authFunctions

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.pz.kebapp.data.SessionManager

fun logoutFunction(context: Context, navHostController: NavHostController) {
    val sessionManager = SessionManager(context)

    sessionManager.removeAuthToken()
    Toast.makeText(context, "Wylogowano", Toast.LENGTH_SHORT).show()
    navHostController.navigate("home")
}