package com.example.trackbuss.domain.data

import com.google.gson.annotations.SerializedName

data class StopPointCod(
    @SerializedName("cp") val stopCode: Int,
    @SerializedName("np") val stopName: String,
    @SerializedName("py") val latitude: Double,
    @SerializedName("px") val longitude: Double,
    @SerializedName("l") val lineList: List<StopLine>
)
