package com.example.trackbuss.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.trackbuss.domain.data.ArrivalForecast
import com.example.trackbuss.presentation.viewmodels.GetArrivalForecastForLineViwModel

@Composable
fun ArrivalForecastScreen(
    lineCode: Int,
    getArrivalForecastForLineViwModel: GetArrivalForecastForLineViwModel,
    modifier: Modifier = Modifier
) {
    val arrivalForecasts by getArrivalForecastForLineViwModel.data.collectAsStateWithLifecycle()
    Column(modifier = modifier.fillMaxSize()) {
        LazyColumn {
            items(items = arrivalForecasts) { arrivalForecast ->
                ArrivalForecastCard(
                    arrivalForecast
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArrivalForecastCard(
    arrivalForecast: ArrivalForecast
) {
    Card(
        onClick = { },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        content = {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    imageVector = Icons.Default.DirectionsBus,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(3.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.size(16.dp))
                Column {
                    Text(
                        text = arrivalForecast.stop.stopName,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = "Código da parada: ${arrivalForecast.stop.stopCode}",
                        fontWeight = FontWeight.Bold,
                    )
                    LazyRow {
                        items(items = arrivalForecast.stop.vehicleList){vehicle->
                            Text(text = vehicle.estimatedTimeStop)
                            Spacer(modifier = Modifier.size(10.dp))
                        }
                    }
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
    )
}