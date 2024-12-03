package com.pz.kebapp.screens

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.pz.kebapp.R
import com.pz.kebapp.components.MarkerInfoWindowComponent
import com.pz.kebapp.data.models.Data
import com.pz.kebapp.navigation.BottomNavigationBar
import com.pz.kebapp.ui.theme.Background
import com.pz.kebapp.viewModel.DetailsViewModel
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(
    navController: NavHostController
) {
    val detailsViewModel = viewModel<DetailsViewModel>()
    val state = detailsViewModel.state

    val legnicaState = LatLng(51.2070, 16.1753)
    val defaultCameraPosition = CameraPosition.fromLatLngZoom(legnicaState, 12f)
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val mapUiSettings by remember { mutableStateOf(MapUiSettings(compassEnabled = false)) }

    var mapProperties by remember { mutableStateOf(MapProperties(mapType = MapType.NORMAL)) }

    val cameraPositionState = rememberCameraPositionState { position = defaultCameraPosition }

    var isMapLoaded by remember { mutableStateOf(false) }

    var selectedKebab by remember { mutableStateOf<Data?>(null) }

    coroutineScope.launch {
        val mapStyleOptions = loadMapStyle(context)
        mapProperties = mapProperties.copy(mapStyleOptions = mapStyleOptions)
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
                        .testTag("Mapa")
                        .height(450.dp),
                    onMapLoaded = {
                        isMapLoaded = true
                    },
                    cameraPositionState = cameraPositionState,
                    uiSettings = mapUiSettings,
                    properties = mapProperties
                ) {
                    state.kebabs.forEach { data ->
                        val locationState = rememberMarkerState(
                            position = LatLng(data.coordinatesX, data.coordinatesY)
                        )

                        val iconResource = getMarkerIcon(data.status)
                        val icon = BitmapDescriptorFactory.fromResource(iconResource)

                        MarkerInfoWindowContent(
                            state = locationState,
                            icon = icon,
                            draggable = false,
                            onClick = {
                                selectedKebab = data
                                locationState.showInfoWindow()
                                return@MarkerInfoWindowContent true
                            },
                            onInfoWindowClick = {
                                if (selectedKebab != null) {
                                    navController.navigate("details/${selectedKebab!!.id}")
                                    selectedKebab = null
                                }
                            }
                        ) {
                            MarkerInfoWindowComponent(data = data)
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

private fun loadMapStyle(context: Context): MapStyleOptions {
    return MapStyleOptions.loadRawResourceStyle(context, R.raw.map_style)
}

private fun getMarkerIcon(status: String): Int {
    return when (status) {
        "active" -> R.drawable.kebab48x48
        else -> R.drawable.kebab24x24_inactive
    }
}