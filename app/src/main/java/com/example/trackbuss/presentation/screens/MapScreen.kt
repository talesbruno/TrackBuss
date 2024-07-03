package com.example.trackbuss.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.WavingHand
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.trackbuss.R
import com.example.trackbuss.presentation.viewmodels.GetArrivalForecastForStopViewModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(
    getArrivalForecastForStopViewModel: GetArrivalForecastForStopViewModel,
    lineCode: Int,
) {
    val loading by getArrivalForecastForStopViewModel.isLoading.collectAsStateWithLifecycle()
    LaunchedEffect(lineCode) {
        getArrivalForecastForStopViewModel.getArrivalForecastForStop(lineCode)
    }
    val arrivalForecast by getArrivalForecastForStopViewModel.data.collectAsStateWithLifecycle()
    if (loading) {
        SplashScreen()
    }
    if (arrivalForecast == null) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Não há dados disponíveis para exibir o mapa.")
        }
        return
    }
    val stops = arrivalForecast?.stopPointList
    val initialPosition = stops?.let { LatLng(it.latitude, stops.longitude) }
    val cameraPositionState = rememberCameraPositionState {
        position = initialPosition?.let { CameraPosition.fromLatLngZoom(it, 15f) }!!
    }
    val uiSettings by remember {
        mutableStateOf(MapUiSettings(zoomControlsEnabled = true))
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = uiSettings
    ) {
        if (stops != null) {
            MarkerInfoWindow(
                state = MarkerState(position = LatLng(stops.latitude, stops.longitude)),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .border(
                            BorderStroke(1.dp, Color.Black),
                            RoundedCornerShape(10)
                        )
                        .clip(RoundedCornerShape(10))
                        .background(Color.Blue)
                        .padding(20.dp)
                ) {
                    Text("previsões de parada", fontWeight = FontWeight.Bold, color = Color.White)
                    arrivalForecast?.stopPointList?.lineList?.get(6)?.listOfVehicles?.forEach { vehicle ->
                        Text(
                            vehicle.estimatedTimeStop,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
            arrivalForecast?.stopPointList?.lineList?.get(6)?.listOfVehicles?.forEach { vehicle ->
                MarkerComposable(
                    MarkerState(position = LatLng(vehicle.latitude, vehicle.longitude)),
                ){
                    Icon(imageVector = Icons.Filled.DirectionsBus, contentDescription = "")
                }
            }
        }
    }
}