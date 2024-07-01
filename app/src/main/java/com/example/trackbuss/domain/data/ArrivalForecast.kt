package com.example.trackbuss.domain.data

import com.google.gson.annotations.SerializedName

data class ArrivalForecast(
    @SerializedName("hr") val hr: String,
    @SerializedName("ps") val stop: List<StopPoint>
)

