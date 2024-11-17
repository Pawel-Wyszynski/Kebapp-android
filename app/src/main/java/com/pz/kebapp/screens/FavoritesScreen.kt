package com.pz.kebapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pz.kebapp.R
import com.pz.kebapp.components.HeadingTextComponent
import com.pz.kebapp.components.ImageComponent
import com.pz.kebapp.components.KebabItemComponent
import com.pz.kebapp.navigation.BottomNavigationBar
import com.pz.kebapp.ui.theme.Background
import com.pz.kebapp.viewModel.UserViewModel

@Composable
fun FavoritesScreen(
    navController: NavHostController
) {
    val context = LocalContext.current
    val userViewModel = remember { UserViewModel(context) }
    val state = userViewModel.state

    var isListLoaded by remember {
        mutableStateOf(state.likedKebabs.isNotEmpty())
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(screen = "Ulubione", navController)
        },
        content = { paddingValues ->
            Surface(
                color = Background,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Background)
                    .padding(28.dp, 28.dp, 28.dp, paddingValues.calculateBottomPadding())
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    if (!isListLoaded) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            item {
                                Column {
                                    ImageComponent(
                                        painterResource = painterResource(id = R.drawable.brodacz)
                                    )
                                    Spacer(modifier = Modifier.height(20.dp))

                                    HeadingTextComponent(value = "Ulubione restauracje z kebabami")

                                    Spacer(modifier = Modifier.height(16.dp))
                                }
                            }
                            items(state.likedKebabs) { kebab ->
                                KebabItemComponent(
                                    kebab = kebab,
                                    icon = Icons.AutoMirrored.Filled.ArrowForwardIos,
                                    onSelect = {
                                        navController.navigate("details/${kebab.id}")
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    )
    if (state.likedKebabs.isNotEmpty() && !isListLoaded) {
        isListLoaded = true
    }
}