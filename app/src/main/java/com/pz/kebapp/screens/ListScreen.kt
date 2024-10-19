package com.pz.kebapp.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pz.kebapp.R
import com.pz.kebapp.components.HeadingTextComponent
import com.pz.kebapp.components.ImageComponent
import com.pz.kebapp.components.KebabItemComponent
import com.pz.kebapp.navigation.BottomNavigationBar
import com.pz.kebapp.ui.theme.Background
import com.pz.kebapp.viewModel.KebabViewModel

@Composable
fun ListScreen(
    navController: NavHostController
) {
    val kebabViewModel = viewModel<KebabViewModel>()
    val state = kebabViewModel.state
    val context = LocalContext.current

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
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    item {
                        Column {
                            ImageComponent(
                                painterResource = painterResource(id = R.drawable.brodacz)
                            )
                            Spacer(modifier = Modifier.height(20.dp))

                            HeadingTextComponent(value = "Lista restauracji z kebabami")

                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                    items(state.kebabs) { kebab ->
                        KebabItemComponent(
                            kebab = kebab,
                            icon = Icons.AutoMirrored.Filled.ArrowForwardIos,
                            onSelect = {
                                navController.navigate("details/${kebab.id}")
                            }
                        )
                    }
                    item {
                        if (state.isLoading) {
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                        if (!state.error.isNullOrEmpty()) {
                            Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                if (!state.endReached && !state.isLoading) {
                    kebabViewModel.loadNextKebabs()
                }
            }
        }
    )
}

@Preview
@Composable
fun ListScreenPreview() {
    ListScreen(rememberNavController())
}