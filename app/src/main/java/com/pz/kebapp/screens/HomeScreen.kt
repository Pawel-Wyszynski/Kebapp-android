package com.pz.kebapp.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.pz.kebapp.navigation.BottomNavigationBar
import com.pz.kebapp.ui.theme.Background

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    val legnicaState = LatLng(51.2070, 16.1553)

    val defaultCameraPosition = CameraPosition.fromLatLngZoom(legnicaState, 4f)

    val locationState = rememberMarkerState(
        position = legnicaState
    )

    val mapUiSettings by remember {
        mutableStateOf(MapUiSettings(compassEnabled = false))
    }

    val mapProperties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }

    val cameraPositionState = rememberCameraPositionState {
        position = defaultCameraPosition
    }

    var isMapLoaded by remember {
        mutableStateOf(false)
    }

    var showInfoWindow by remember {
        mutableStateOf(true)
    }

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
                    .padding(0.dp, 0.dp, 0.dp, paddingValues.calculateBottomPadding())
            ) {
                GoogleMap(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp),
                    onMapLoaded = {
                        isMapLoaded = true
                    },
                    cameraPositionState = cameraPositionState,
                    uiSettings = mapUiSettings,
                    properties = mapProperties
                ) {
                    MarkerInfoWindowContent(
                        state = locationState,
                        draggable = true,
                        onClick = {
                            if (showInfoWindow) {
                                locationState.showInfoWindow()
                            } else {
                                locationState.hideInfoWindow()
                            }
                            showInfoWindow = !showInfoWindow
                            return@MarkerInfoWindowContent false
                        },
                        title = "Legnica Map Title"
                    ) {
                        Column {
                            Text(text = "ZAHIR")
                            Text(text = "KEBAB")
                            Text(text = "LEGNICA")
                        }
                    }
                }
                if (!isMapLoaded) {
                    AnimatedVisibility(visible = !isMapLoaded) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .wrapContentSize()
                        )
                    }
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