package com.pz.kebapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pz.kebapp.screens.ContactUsScreen
import com.pz.kebapp.screens.FavoritesScreen
import com.pz.kebapp.screens.GuestScreen
import com.pz.kebapp.screens.HomeScreen
import com.pz.kebapp.screens.KebabDetailsScreen
import com.pz.kebapp.screens.ListScreen
import com.pz.kebapp.screens.auth.LoginScreen
import com.pz.kebapp.screens.auth.SignUpScreen
import com.pz.kebapp.viewModel.DetailsViewModel

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
        composable("details/{id}",
            arguments = listOf(
                navArgument(
                    name = "id"
                ) {
                    type = NavType.IntType
                }
            )
        ) {
            val viewModel: DetailsViewModel = viewModel()
            it.arguments?.getInt("id")?.let { id ->
                KebabDetailsScreen(id = id, navController = navController, viewModel = viewModel)
            }
        }
    }
}