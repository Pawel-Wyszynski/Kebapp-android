package com.pz.kebapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pz.kebapp.screens.ContactUsScreen
import com.pz.kebapp.screens.FavoritesScreen
import com.pz.kebapp.screens.GuestScreen
import com.pz.kebapp.screens.HomeScreen
import com.pz.kebapp.screens.ListScreen
import com.pz.kebapp.screens.auth.LoginScreen
import com.pz.kebapp.screens.auth.SignUpScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("login") { LoginScreen(navController) }
        composable("register") { SignUpScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("list") { ListScreen(navController) }
        composable("favorites") { FavoritesScreen(navController) }
        composable("contactus") { ContactUsScreen(navController) }
        composable("guest") { GuestScreen(navController) }
    }
}