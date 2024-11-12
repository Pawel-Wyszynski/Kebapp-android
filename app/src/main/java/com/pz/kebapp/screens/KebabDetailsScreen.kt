package com.pz.kebapp.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pz.kebapp.components.KebabDetailsComponent
import com.pz.kebapp.navigation.BottomNavigationBar
import com.pz.kebapp.viewModel.DetailsViewModel

@Composable
fun KebabDetailsScreen(
    id: Int,
    navController: NavHostController,
    viewModel: DetailsViewModel
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(screen = "Kebaby", navController)
        },
        content = { paddingValues ->
            KebabDetailsComponent(id, viewModel, paddingValues)
        }
    )
}

@Preview
@Composable
fun DetailsScreenPreview() {
    KebabDetailsScreen(
        id = 1,
        navController = rememberNavController(),
        viewModel = DetailsViewModel()
    )
}
