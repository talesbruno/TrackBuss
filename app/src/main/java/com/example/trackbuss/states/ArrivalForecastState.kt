package com.example.trackbuss.states

import com.example.trackbuss.domain.data.ArrivalForecast
import com.example.trackbuss.domain.data.StopPoint
import com.example.trackbuss.domain.data.Vehicle
import com.google.gson.annotations.SerializedName

data class ArrivalForecastState(
    val hr: String? = null,
    val stop: StopPointState = StopPointState()
)

data class StopPointState(
    val stopCode: Int? = null,
    val stopName: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val vehicleList: List<Vehicle> = emptyList()
)

data class VehicleState(
    val p: Int? = null,
    val a: Boolean? = null,
    val estimatedTimeStop: String? = null,
    val timestamp: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
)