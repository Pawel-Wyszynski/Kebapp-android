package com.pz.kebapp.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.pz.kebapp.ui.theme.Navbar
import com.pz.kebapp.ui.theme.nunitoSansFontFamily


data class BottomNavItem(
    val route: () -> Unit,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

@Composable
fun BottomNavigationBar(screen: String, navHostController: NavHostController) {
    val bottomNavItems = listOf(
        BottomNavItem(
            route = { navHostController.navigate("home") },
            label = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),

        BottomNavItem(
            route = { navHostController.navigate("list") },
            label = "Kebaby",
            selectedIcon = Icons.AutoMirrored.Filled.List,
            unselectedIcon = Icons.AutoMirrored.Outlined.List
        ),

        BottomNavItem(
            route = { navHostController.navigate("favorites") },
            label = "Ulubione",
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Outlined.Favorite
        ),

        BottomNavItem(
            route = { navHostController.navigate("contactus") },
            label = "Kontakt",
            selectedIcon = Icons.Filled.Mail,
            unselectedIcon = Icons.Outlined.Mail
        ),

        BottomNavItem(
            route = { navHostController.navigate("login") },
            label = "Wyloguj",
            selectedIcon = Icons.AutoMirrored.Filled.Logout,
            unselectedIcon = Icons.AutoMirrored.Outlined.Logout
        )
    )
    NavigationBar(containerColor = Navbar) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            bottomNavItems.forEach { item ->
                NavigationBarItem(
                    selected = screen == item.label,
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Navbar
                    ),
                    onClick = item.route,
                    icon = {
                        Icon(
                            imageVector =
                            if (screen == item.label)
                                item.selectedIcon else item.unselectedIcon,
                            contentDescription = null,
                            tint = if (screen == item.label)
                                Color.Black else Color.Gray
                        )
                    },
                    label = {
                        Text(
                            text = item.label,
                            fontFamily = nunitoSansFontFamily,
                            color = if (screen == item.label)
                                Color.Black else Color.Gray
                        )
                    }
                )
            }
        }
    }
}