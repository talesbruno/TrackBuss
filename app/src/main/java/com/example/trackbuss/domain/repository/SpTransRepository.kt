package com.example.trackbuss.domain.repository

import com.example.trackbuss.domain.data.ArrivalForecast
import com.example.trackbuss.domain.data.BusLine
import com.example.trackbuss.domain.data.BusStop
import com.example.trackbuss.domain.data.DataResponse
import com.example.trackbuss.utils.Result
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface SpTransRepository {
    suspend fun authenticate(apiKey: String): Flow<Result<Boolean>>

    suspend fun getBusPositions(): Flow<Result<DataResponse>>

    suspend fun searchLines(searchTerm: String): Flow<Result<List<BusLine>>>  // Sem authToken

    suspend fun searchStops(searchTerm: String): Flow<Result<List<BusStop>>>  // Sem authToken

    suspend fun getArrivalForecast(stopCode: Int, lineCode: String): Flow<Result<List<ArrivalForecast>>>  // Sem authToken

    suspend fun getArrivalForecastForLine(lineCode: String): Flow<Result<List<ArrivalForecast>>>  // Sem authToken

    suspend fun getArrivalForecastForStop(stopCode: Int): Flow<Result<ArrivalForecast>>  // Sem authToken
}