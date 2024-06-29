package com.example.trackbuss.domain.data

import com.google.gson.annotations.SerializedName

data class BusStop(
    @SerializedName("cp") val stopCode: Int,
    @SerializedName("np") val stopName: String,
    @SerializedName("ed") val stopAddress: String,
    @SerializedName("py") val latitude: Double,
    @SerializedName("px") val longitude: Double
)