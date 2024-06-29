package com.example.trackbuss.domain.data

import com.google.gson.annotations.SerializedName

data class StopLine(
    @SerializedName("c") val sign: String,
    @SerializedName("lc") val lineName: String,
    @SerializedName("sl") val directionOfOperation: Int,
    @SerializedName("lt0") val destinationSign: String,
    @SerializedName("lt1") val originSign: String,
    @SerializedName("qv") val numberOfVehicles : Int,
    @SerializedName("vs") val listOfVehicles: List<Vehicle>
)
