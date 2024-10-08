package com.pz.kebapp.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pz.kebapp.ContactUsScreen
import com.pz.kebapp.FavoritesScreen
import com.pz.kebapp.HomeScreen
import com.pz.kebapp.ListScreen
import com.pz.kebapp.R
import com.pz.kebapp.components.HeadingTextComponent
import com.pz.kebapp.ui.theme.Navbar
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavDrawer(
    navController: NavHostController
) {
    val items = listOf(
        Items(Icons.Default.Home, "Home", route = { navController.navigate("home") }),
        Items(
            Icons.Default.ThumbUp,
            "Favorites kebab shops",
            route = { navController.navigate("favorites") }),
        Items(
            Icons.Default.FilterList,
            "List of kebab shops",
            route = { navController.navigate("list") }),
        Items(
            Icons.Default.ContactPage,
            "Contact us",
            route = { navController.navigate("contactus") }),
        Items(Icons.Default.Logout, "Logout", route = { navController.navigate("home") }),
    )

    var selectedItem by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)


    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet {
                Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.brodacz),
                            contentDescription = "logo pic",
                            modifier = Modifier
                                .size(160.dp)
                                .clip(CircleShape)
                        )
                        Divider(
                            modifier = Modifier.align(Alignment.BottomCenter),
                            thickness = 1.dp,
                        )
                    }
                    items.forEachIndexed() { index, it ->
                        NavigationDrawerItem(
                            label = { Text(text = it.text) },
                            selected = selectedItem == index,
                            icon = { Icon(imageVector = it.icon, contentDescription = it.text) },
                            modifier = Modifier.padding(horizontal = 16.dp),
                            onClick = {
                                selectedItem = index
                                coroutineScope.launch {
                                    drawerState.close()
                                }
                            }
                        )
                    }
                }
            }
        },
    ) {
        Scaffold(
            topBar = {
                TopAppBar(title = { HeadingTextComponent(value = "Kebapp") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Navbar,
                        titleContentColor = Color.White,
                        navigationIconContentColor = Color.White
                    ),
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                Icons.Rounded.Menu,
                                contentDescription = "MenuButton"
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            ContentScreen(modifier = Modifier.padding(innerPadding), selectedItem)
        }
    }
}

@Composable
fun ContentScreen(modifier: Modifier, selectedItem: Int) {
    when (selectedItem) {
        0 -> HomeScreen(rememberNavController())
        1 -> FavoritesScreen(rememberNavController())
        2 -> ListScreen(rememberNavController())
        3 -> ContactUsScreen(rememberNavController())
        4 -> HomeScreen(rememberNavController())
    }
}

@Preview
@Composable
fun NavDrawerPreview() {
    NavDrawer(rememberNavController())
}

data class Items(
    val icon: ImageVector,
    val text: String,
    val route: () -> Unit
)
