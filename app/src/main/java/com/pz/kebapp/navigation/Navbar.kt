package com.pz.kebapp.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.Logout
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
import com.pz.kebapp.R
import com.pz.kebapp.components.HeadingTextComponent
import com.pz.kebapp.screens.ContactUsScreen
import com.pz.kebapp.screens.FavoritesScreen
import com.pz.kebapp.screens.HomeScreen
import com.pz.kebapp.screens.ListScreen
import com.pz.kebapp.screens.auth.LoginScreen
import com.pz.kebapp.ui.theme.Navbar
import kotlinx.coroutines.launch

data class NavItem(
    val icon: ImageVector,
    val text: String,
    val route: () -> Unit
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navbar(
    navController: NavHostController
) {
    val navItems = listOf(
        NavItem(
            icon = Icons.Default.Home,
            text = "Home",
            route = { navController.navigate("home") }
        ),
        NavItem(
            icon = Icons.Default.Favorite,
            text = "Ulubione kebaby",
            route = { navController.navigate("favorites") }),
        NavItem(
            icon = Icons.AutoMirrored.Filled.List,
            text = "Lista kebabów",
            route = { navController.navigate("list") }),
        NavItem(
            icon = Icons.Default.Mail,
            text = "Napisz do nas",
            route = { navController.navigate("contactus") }),
        NavItem(
            icon = Icons.AutoMirrored.Filled.Logout,
            text = "Wyloguj",
            route = { navController.navigate("login") }
        ),
    )

    var selectedItem by remember { mutableIntStateOf(0) }
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.brodacz),
                            contentDescription = null,
                            modifier = Modifier
                                .size(160.dp)
                                .clip(CircleShape)
                        )
                        Divider(
                            modifier = Modifier.align(Alignment.BottomCenter),
                            thickness = 1.dp,
                        )
                    }
                    navItems.forEachIndexed { index, it ->
                        NavigationDrawerItem(
                            label = { Text(text = it.text) },
                            selected = selectedItem == index,
                            icon = { Icon(imageVector = it.icon, contentDescription = null) },
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
                                contentDescription = null
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
        4 -> LoginScreen(rememberNavController())
    }
}

@Preview
@Composable
fun NavbarPreview() {
    Navbar(rememberNavController())
}