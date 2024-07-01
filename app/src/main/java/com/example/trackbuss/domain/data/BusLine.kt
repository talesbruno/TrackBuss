package com.example.trackbuss.domain.data

import com.google.gson.annotations.SerializedName

data class BusLine(
    @SerializedName("cl") val lineCode: Int,
    @SerializedName("lc") val circular: Boolean,
    @SerializedName("lt") val firstName: String,
    @SerializedName("sl") val sense: Int,
    @SerializedName("tl") val lastName: Int,
    @SerializedName("tp") val mainSign: String,
    @SerializedName("ts") val secondarySign: String,
)
