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
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.pz.kebapp.R
import com.pz.kebapp.components.HeadingTextComponent
import com.pz.kebapp.components.ImageComponent
import com.pz.kebapp.components.KebabItemComponent
import com.pz.kebapp.filters.DrawerMenu
import com.pz.kebapp.filters.FilterItem
import com.pz.kebapp.filters.TopBar
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

    val filters = listOf(
        FilterItem("isOpenNow", "Otwarte teraz"),
        FilterItem("isCraft", "Rzemieślnicze"),
        FilterItem("status", "Aktywny"),
        FilterItem("status", "Nieaktywny"),
        FilterItem("status", "Planowany"),
        FilterItem("isChainStore", "Sieciówka"),
        FilterItem("isFoodTruck", "Food truck")
    )

    val meatFilterState = remember { mutableStateListOf(false, false, false, false, false) }
    val sauceFilterState = remember { mutableStateListOf(false, false, false, false) }
    var selectedSort = remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            DrawerMenu(
                filters = filters,
                meatFilterState = meatFilterState,
                sauceFilterState = sauceFilterState,
                selectedSort = selectedSort,
                coroutineScope = coroutineScope,
                drawerState = drawerState
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    coroutineScope = coroutineScope,
                    drawerState = drawerState
                )
            },
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
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 16.dp)
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
}