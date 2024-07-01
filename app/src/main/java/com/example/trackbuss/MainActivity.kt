package com.example.trackbuss

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.trackbuss.presentation.screens.HomeScreen
import com.example.trackbuss.presentation.viewmodels.GetArrivalForecastForLineViwModel
import com.example.trackbuss.presentation.viewmodels.GetArrivalForecastForStopViewModel
import com.example.trackbuss.presentation.viewmodels.SearchLinesViewModel
import com.example.trackbuss.presentation.viewmodels.SearchStopsViewModel
import com.example.trackbuss.ui.theme.TrackBussTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val getArrivalForecastForLineViwModel by viewModels<GetArrivalForecastForLineViwModel>()
    private val getArrivalForecastForStopViewModel by viewModels<GetArrivalForecastForStopViewModel>()
    private val searchLinesViewModel by viewModels<SearchLinesViewModel>()
    private val searchStopsViewModel by viewModels<SearchStopsViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrackBussTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(
                        getArrivalForecastForLineViwModel,
                        getArrivalForecastForStopViewModel,
                        searchLinesViewModel,
                        searchStopsViewModel,
                    )
                }
            }
        }
    }
}