package com.example.trackbuss.domain.data

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("c") val code: String,
    @SerializedName("cl") val cl: Int,
    @SerializedName("sl") val sl: Int,
    @SerializedName("lt0") val location0: String,
    @SerializedName("lt1") val location1: String,
    @SerializedName("qv") val qv: Int,
    @SerializedName("vs") val vehicles: List<Vehicle>
)
