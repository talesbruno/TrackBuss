package com.example.trackbuss.domain.data

import com.google.gson.annotations.SerializedName

data class BusLine(
    @SerializedName("cl") val lineCode: String,
    @SerializedName("lc") val lineName: String,
    @SerializedName("lt") val lineType: Int,
    @SerializedName("tl") val lineOperationTime: String,
    @SerializedName("ts") val lineDaysOfOperation: String,
    @SerializedName("tp") val lineExtension: Int
)
