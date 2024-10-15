package com.pz.kebapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pz.kebapp.R
import com.pz.kebapp.components.HeadingTextComponent
import com.pz.kebapp.components.ImageComponent
import com.pz.kebapp.navigation.BottomNavigationBar
import com.pz.kebapp.ui.theme.Background

@Composable
fun DetailsScreen(
    id: Int,
    navController: NavHostController // Dodaj navController, jeśli planujesz używać go do nawigacji
) {
    val scrollState = rememberScrollState()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(screen = "Kebaby", navController)
        },
        content = { paddingValues ->
            Surface(
                color = Background,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Background)
                    .padding(28.dp, 28.dp, 28.dp, paddingValues.calculateBottomPadding())
                    .verticalScroll(scrollState)
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    ImageComponent(
                        painterResource = painterResource(id = R.drawable.brodacz)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    // Wyświetl id kebaba
                    HeadingTextComponent(value = "Kliknąłeś w kebaba o ID: $id")
                }
            }
        }
    )
}

@Preview
@Composable
fun DetailsScreenPreview() {
    DetailsScreen(id = 1, navController = rememberNavController())
}
