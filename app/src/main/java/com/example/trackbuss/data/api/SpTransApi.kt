package com.example.trackbuss.data.api

import com.example.trackbuss.domain.data.ArrivalForecast
import com.example.trackbuss.domain.data.BusLine
import com.example.trackbuss.domain.data.BusStop
import com.example.trackbuss.domain.data.DataResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SpTransApi {

    @GET("Posicao")
    suspend fun getBusPositions(): DataResponse

    @GET("Linha/Buscar")
    suspend fun searchLines(@Query("termosBusca") searchTerm: String): List<BusLine>

    @GET("Parada/Buscar")
    suspend fun searchStops(@Query("termosBusca") searchTerm: String): List<BusStop>

    @GET("Previsao")
    suspend fun getArrivalForecast(
        @Query("codigoParada") stopCode: Int,
        @Query("codigoLinha") lineCode: String
    ): List<ArrivalForecast>
}