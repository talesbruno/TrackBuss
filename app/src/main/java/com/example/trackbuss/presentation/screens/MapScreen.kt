package com.example.trackbuss.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.trackbuss.presentation.viewmodels.GetArrivalForecastForLineViwModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(
    getArrivalForecastForLineViwModel: GetArrivalForecastForLineViwModel,
    lineCode: Int,
    modifier: Modifier = Modifier
) {
    val loading by getArrivalForecastForLineViwModel.isLoading.collectAsStateWithLifecycle()
    val arrivalForecast by getArrivalForecastForLineViwModel.data.collectAsStateWithLifecycle()
    if (loading) {
        SplashScreen()
    }
    LaunchedEffect(lineCode) {
        getArrivalForecastForLineViwModel.getArrivalForecastForLine(lineCode)
    }
    if (arrivalForecast == null || arrivalForecast!!.stop.isEmpty()) {
        // Trata o caso em que positionData é nulo ou não há paradas disponíveis
        Text("Não há dados disponíveis para exibir o mapa.")
        return
    }
    val stops = arrivalForecast?.stop
    val initialPosition = stops?.get(0)?.let { LatLng(it.latitude, stops[0].longitude) }
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
    ){
        stops?.forEachIndexed { index, stop ->
            Marker(
                state = MarkerState(position = LatLng(stop.latitude, stop.longitude)),
                title = stop.stopName
            )
        }
    }
}