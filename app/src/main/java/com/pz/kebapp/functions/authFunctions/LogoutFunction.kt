package com.pz.kebapp.functions.authFunctions

import android.content.Context
import androidx.navigation.NavHostController
import com.pz.kebapp.data.SessionManager

fun logoutFunction(context: Context, navHostController: NavHostController) {
    val sessionManager = SessionManager(context)

    sessionManager.removeAuthToken()
    navHostController.navigate("home")
}