package com.example.trackbuss.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.trackbuss.domain.data.ArrivalForecast
import com.example.trackbuss.domain.data.BusStop
import com.example.trackbuss.presentation.components.GenericSearchBar
import com.example.trackbuss.presentation.viewmodels.GetArrivalForecastForLineViwModel
import com.example.trackbuss.presentation.viewmodels.SearchStopsViewModel
import com.example.trackbuss.states.SearchEvent

@Composable
fun StopPintsScreen(
    lineCode: Int,
    searchStopsViewModel: SearchStopsViewModel,
    onNavigateToMapsScreen: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val state = searchStopsViewModel.searchState
    val loading by searchStopsViewModel.isLoading.collectAsStateWithLifecycle()
    if (loading) {
        SplashScreen()
    }
    LaunchedEffect(lineCode) {
        searchStopsViewModel.searchStops(lineCode)
    }
    val busStops by searchStopsViewModel.data.collectAsStateWithLifecycle()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize().padding(16.dp)
    ) {
        GenericSearchBar(
            items = busStops,
            query = state.query,
            onQueryChange = { newQuery ->
                searchStopsViewModel.onEvent(SearchEvent.QueryChanged(newQuery))
            },
            onSearch = {
                searchStopsViewModel.onEvent(SearchEvent.Submit)
            },
            placeholder = {
                Text("Filtrar...")
            },
            itemContent = { busStop ->
                ArrivalForecastCard(
                    busStop,
                    onNavigateToMapsScreen
                )
            }
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = "PONTOS DE PARADA",
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.size(16.dp))
        LazyColumn{
            items(items = busStops) { busStop ->
                ArrivalForecastCard(
                    busStop,
                    onNavigateToMapsScreen
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArrivalForecastCard(
    busStop: BusStop,
    onNavigateToMapsScreen: (Int) -> Unit,
) {
    Card(
        onClick = { onNavigateToMapsScreen(busStop.stopCode) },
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
                        text = busStop.stopName,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth().padding(6.dp)
    )
}
