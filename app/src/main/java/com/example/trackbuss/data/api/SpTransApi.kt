package com.example.trackbuss.data.api

import com.example.trackbuss.domain.data.ArrivalForecast
import com.example.trackbuss.domain.data.BusLine
import com.example.trackbuss.domain.data.BusStop
import com.example.trackbuss.domain.data.DataResponse
import com.example.trackbuss.utils.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface SpTransApi {


    @GET(value = "Posicao")
    suspend fun getBusPositions(): DataResponse

    @GET(value = "Linha/Buscar")
    suspend fun searchLines(@Query("termosBusca") searchTerm: String): List<BusLine>

    @GET(value = "Parada/BuscarParadasPorLinha")
    suspend fun searchStops(@Query("codigoLinha") searchTerm: Int): List<BusStop>

    @GET(value = "Previsao")
    suspend fun getArrivalForecast(
        @Query("codigoParada") stopCode: Int,
        @Query("codigoLinha") lineCode: String
    ): List<ArrivalForecast>

    @GET(value = "Previsao/Linha")
    suspend fun getArrivalForecastForLine(
        @Query("codigoLinha") lineCode: Int
    ): ArrivalForecast

    @GET(value = "Previsao/Parada")
    suspend fun getArrivalForecastForStop(
        @Query("codigoParada") stopCode: Int
    ): ArrivalForecast

}