package com.pz.kebapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
fun HomeScreen(
    navController: NavHostController
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(screen = "Home", navController)
        },
        content = { paddingValues ->
            Surface(
                color = Background,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Background)
                    .padding(28.dp, 28.dp, 28.dp, paddingValues.calculateBottomPadding())
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    ImageComponent(
                        painterResource = painterResource(id = R.drawable.brodacz)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    HeadingTextComponent(value = "Home")
                }
            }
        }
    )
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}