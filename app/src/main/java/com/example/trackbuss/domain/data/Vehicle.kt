package com.example.trackbuss.domain.data

import com.google.gson.annotations.SerializedName

data class Vehicle(
    @SerializedName("p") val p: Int,
    @SerializedName("a") val a: Boolean,
    @SerializedName("t") val estimatedTimeStop: String,
    @SerializedName("ta") val timestamp: String,
    @SerializedName("py") val latitude: Double,
    @SerializedName("px") val longitude: Double
)