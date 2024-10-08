package com.pz.kebapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pz.kebapp.ContactUsScreen
import com.pz.kebapp.FavoritesScreen
import com.pz.kebapp.HomeScreen
import com.pz.kebapp.ListScreen
import com.pz.kebapp.screens.auth.LoginScreen
import com.pz.kebapp.screens.auth.SignUpScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "navdrawer") {
        composable("navdrawer") { NavDrawer(navController) }
        composable("login") { LoginScreen(navController) }
        composable("register") { SignUpScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("list") { ListScreen(navController) }
        composable("favorites") { FavoritesScreen(navController) }
        composable("contactus") { ContactUsScreen(navController) }
    }
}