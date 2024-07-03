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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.trackbuss.domain.data.BusLine
import com.example.trackbuss.presentation.components.GenericSearchBar
import com.example.trackbuss.presentation.viewmodels.SearchLinesViewModel
import com.example.trackbuss.states.SearchEvent

@Composable
fun LineListScreen(
    searchLinesViewModel: SearchLinesViewModel,
    onNavigateToArrivalForecastScreen: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val state = searchLinesViewModel.searchState
    val lines by searchLinesViewModel.data.collectAsStateWithLifecycle()
    val loading by searchLinesViewModel.isLoading.collectAsStateWithLifecycle()

    if (loading) {
        SplashScreen()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        GenericSearchBar(
            items = lines,
            query = state.query,
            onQueryChange = { newQuery ->
                searchLinesViewModel.onEvent(SearchEvent.QueryChanged(newQuery))
            },
            onSearch = {
                searchLinesViewModel.onEvent(SearchEvent.Submit)
            },
            placeholder = {
                Text("Buscar linha...")
            },
            itemContent = { busLine ->
                LineCard(
                    busLine.lineCode,
                    busLine.firstName,
                    busLine.lastName,
                    busLine.mainSign,
                    busLine.secondarySign,
                    onNavigateToArrivalForecastScreen
                )
            }
        )
        Spacer(modifier = Modifier.size(22.dp))
        LazyColumn {
            items(items = lines) { busLine ->
                LineCard(
                    busLine.lineCode,
                    busLine.firstName,
                    busLine.lastName,
                    busLine.mainSign,
                    busLine.secondarySign,
                    onNavigateToArrivalForecastScreen
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LineCard(
    lineCode: Int,
    firstName: String,
    lastName: Int,
    mainSign: String,
    secondarySign: String,
    onNavigateToArrivalForecastScreen: (Int) -> Unit,
) {
    Card(
        onClick = { onNavigateToArrivalForecastScreen(lineCode) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        content = {
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    imageVector = Icons.Default.DirectionsBus,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(3.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.size(4.dp))
                Column {
                    Row {
                        Text(
                            text = "${firstName}-${lastName}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = mainSign,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Row {
                        Text(
                            text = "${secondarySign}/${mainSign}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                        )
                    }
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    )
}