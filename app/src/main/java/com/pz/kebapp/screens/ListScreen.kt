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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.pz.kebapp.viewModel.KebabViewModel

@Composable
fun ListScreen(
    navController: NavHostController,
    viewModel: KebabViewModel
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val kebabsData = viewModel.kebabsData.collectAsState().value

    LaunchedEffect(key1 = true) {
        viewModel.getKebabs(context)
    }

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

                    HeadingTextComponent(value = "Lista kebabów")

                    Text(text = kebabsData.toString())
                }
            }
        }
    )
}

@Preview
@Composable
fun ListScreenPreview() {
    ListScreen(rememberNavController(), KebabViewModel())
}