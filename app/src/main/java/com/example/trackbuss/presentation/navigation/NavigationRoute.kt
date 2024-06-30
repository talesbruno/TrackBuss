package com.example.trackbuss.presentation.navigation

sealed class NavigationRoute(val route: String) {
    object Lines : NavigationRoute(route = "lines")
    object ArrivalForecast : NavigationRoute(route = "arrivalForecast/{lineCode}"){
        fun createRoute(lineCode: Int) = "arrivalForecast/$lineCode"
    }
}