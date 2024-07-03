package com.example.trackbuss.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.trackbuss.domain.data.ArrivalForecast
import com.example.trackbuss.domain.data.BusLine
import com.example.trackbuss.presentation.navigation.NavigationRoute
import com.example.trackbuss.presentation.viewmodels.GetArrivalForecastForLineViwModel
import com.example.trackbuss.presentation.viewmodels.GetArrivalForecastForStopViewModel
import com.example.trackbuss.presentation.viewmodels.SearchLinesViewModel
import com.example.trackbuss.presentation.viewmodels.SearchStopsViewModel
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen(
    getArrivalForecastForLineViwModel: GetArrivalForecastForLineViwModel,
    getArrivalForecastForStopViewModel: GetArrivalForecastForStopViewModel,
    searchLinesViewModel: SearchLinesViewModel,
    searchStopsViewModel: SearchStopsViewModel,
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawer(
                navigateToLinesScree = { navController.navigate(NavigationRoute.Lines.route) },
                navigateToArrivalForecast = { navController.navigate(NavigationRoute.ArrivalForecast.route) },
                closeDrawer = {
                    scope.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                }
            )
        },
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "TrackBuss",
                            color = MaterialTheme.colorScheme.surface
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primary),
                    modifier = Modifier.fillMaxWidth(),
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }, content = {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu icon",
                                tint = MaterialTheme.colorScheme.surface
                            )
                        })
                    },
                )
            }
        ) { padding ->
            NavHost(
                navController = navController,
                startDestination = NavigationRoute.Lines.route,
                modifier = Modifier.padding(padding)
            ) {
                composable(NavigationRoute.Lines.route) {
                    LineListScreen(
                        searchLinesViewModel = searchLinesViewModel,
                        onNavigateToArrivalForecastScreen = {
                            navController.navigate(NavigationRoute.ArrivalForecast.createRoute(it))
                        }
                    )
                }
                composable(
                    NavigationRoute.ArrivalForecast.route,
                    arguments = listOf(navArgument("lineCode") { type = NavType.IntType })
                ) { backStackEntry ->
                    val lineCode = backStackEntry.arguments?.getInt("lineCode")
                    requireNotNull(lineCode)
                    StopPintsScreen(
                        lineCode,
                        searchStopsViewModel,
                        onNavigateToMapsScreen = {
                            navController.navigate(NavigationRoute.Maps.createRoute(it))
                        }
                    )
                }
                composable(
                    NavigationRoute.Maps.route,
                    arguments = listOf(navArgument("lineCode") { type = NavType.IntType })
                ) { backStackEntry ->
                    val lineCode = backStackEntry.arguments?.getInt("lineCode")
                    requireNotNull(lineCode)
                    MapScreen(
                        getArrivalForecastForStopViewModel = getArrivalForecastForStopViewModel,
                        lineCode = lineCode
                    )
                }
            }
        }
    }
}


@Composable
fun AppDrawer(
    modifier: Modifier = Modifier,
    navigateToLinesScree: () -> Unit,
    navigateToArrivalForecast: () -> Unit,
    closeDrawer: () -> Unit,
) {
    ModalDrawerSheet(modifier = Modifier) {
        DrawerHeader(
            modifier,
        )
        Spacer(modifier = Modifier.padding(5.dp))
        NavigationDrawerItem(
            label = {
                Text(
                    text = "Exibir Linhas",
                    style = MaterialTheme.typography.labelSmall
                )
            },
            selected = false,
            onClick = {
                navigateToLinesScree()
                closeDrawer()
            },
            shape = MaterialTheme.shapes.small,
            icon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null
                )
            }
        )
    }
}

@Composable
fun DrawerHeader(
    modifier: Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary)
            .padding(15.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "teste",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}
