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
    suspend fun getBusPositions(): Flow<Result<DataResponse>>

    suspend fun searchLines(searchTerm: String): Flow<Result<List<BusLine>>>

    suspend fun searchStops(searchTerm: Int): Flow<Result<List<BusStop>>>

    suspend fun getArrivalForecast(stopCode: Int, lineCode: String): Flow<Result<List<ArrivalForecast>>>

    suspend fun getArrivalForecastForLine(lineCode: Int): Flow<Result<ArrivalForecast>>

    suspend fun getArrivalForecastForStop(stopCode: Int): Flow<Result<DataResponse>>
}