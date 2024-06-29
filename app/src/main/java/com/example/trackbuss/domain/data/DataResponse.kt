package com.example.trackbuss.domain.data

import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("hr") val hr: String,
    @SerializedName("l") val locations: List<Location>
)


