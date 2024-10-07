package com.pz.kebapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContactPage
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pz.kebapp.components.HeadingTextComponent
import com.pz.kebapp.ui.theme.Background
import com.pz.kebapp.ui.theme.Gray
import com.pz.kebapp.ui.theme.KebappTheme
import com.pz.kebapp.ui.theme.Navbar
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KebappTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Background
                ) {
                    NavDrawer()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavDrawer() {

    val Item = listOf(
        Items(Icons.Default.Home, "Home"),
        Items(Icons.Default.ThumbUp, "Favorites kebab shops"),
        Items(Icons.Default.FilterList, "List of kebab shops"),
        Items(Icons.Default.ContactPage, "Contact us"),
        Items(Icons.Default.Logout, "Logout"),
    )
    var selectedItem by remember {
        mutableStateOf(Item[0])
    }
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val context = LocalContext.current.applicationContext


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
                            .background(Background),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            Modifier.wrapContentSize(),
                            verticalArrangement = Arrangement.SpaceAround,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.brodacz),
                                contentDescription = "logo pic",
                                modifier = Modifier
                                    .size(160.dp)
                                    .clip(CircleShape)
                            )
                        }
                        Divider(
                            Modifier.align(Alignment.BottomCenter), thickness = 1.dp,
                        )
                    }
                    Item.forEach {
                        NavigationDrawerItem(
                            label = { Text(text = it.text) },
                            selected = it == selectedItem,
                            icon = {
                                Icon(
                                    imageVector = it.icon,
                                    contentDescription = it.text,
                                    tint = Gray
                                )
                            },
                            modifier = Modifier.padding(horizontal = 16.dp),
                            onClick = {
                                selectedItem = it
                                coroutineScope.launch {
                                    drawerState.close()
                                }
                            })
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
        ) {
        }
    }

}

data class Items(
    val icon: ImageVector,
    val text: String,
)