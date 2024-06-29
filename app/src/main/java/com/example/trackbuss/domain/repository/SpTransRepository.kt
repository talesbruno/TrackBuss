package com.example.trackbuss.domain.repository

import com.example.trackbuss.domain.data.ArrivalForecast
import com.example.trackbuss.domain.data.BusLine
import com.example.trackbuss.domain.data.BusStop
import com.example.trackbuss.domain.data.DataResponse
import com.example.trackbuss.utils.Result
import kotlinx.coroutines.flow.Flow

interface SpTransRepository {
    suspend fun getBusPositions(): Flow<Result<DataResponse>>
    suspend fun searchLines(searchTerm: String): Flow<Result<List<BusLine>>>
    suspend fun searchStops(searchTerm: String): Flow<Result<List<BusStop>>>
    suspend fun getArrivalForecast(stopCode: Int, lineCode: String): Flow<Result<List<ArrivalForecast>>>
}