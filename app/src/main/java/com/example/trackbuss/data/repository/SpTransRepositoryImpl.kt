package com.example.trackbuss.data.repository

import com.example.trackbuss.data.api.SpTransApi
import com.example.trackbuss.domain.data.ArrivalForecast
import com.example.trackbuss.domain.data.BusLine
import com.example.trackbuss.domain.data.BusStop
import com.example.trackbuss.domain.data.DataResponse
import com.example.trackbuss.domain.repository.SpTransRepository
import com.example.trackbuss.utils.Constants.API_KEY
import com.example.trackbuss.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SpTransRepositoryImpl @Inject constructor(private val spTransApi: SpTransApi) :
    SpTransRepository {
    override suspend fun getBusPositions(): Flow<Result<DataResponse>> {
        return flow {
            emit(Result.Loading())
            try {
                val response = spTransApi.getBusPositions()
                emit(Result.Success(response))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }
    }

    override suspend fun searchLines(searchTerm: String): Flow<Result<List<BusLine>>> {
        return flow {
            emit(Result.Loading())
            try {
                val response = spTransApi.searchLines(searchTerm)
                emit(Result.Success(response))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }
    }

    override suspend fun searchStops(searchTerm: Int): Flow<Result<List<BusStop>>> {
        return flow {
            emit(Result.Loading())
            try {
                val response = spTransApi.searchStops(searchTerm)
                emit(Result.Success(response))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }
    }

    override suspend fun getArrivalForecast(
        stopCode: Int,
        lineCode: String
    ): Flow<Result<List<ArrivalForecast>>> {
        return flow {
            emit(Result.Loading())
            try {
                val response = spTransApi.getArrivalForecast(stopCode, lineCode)
                emit(Result.Success(response))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }
    }

    override suspend fun getArrivalForecastForLine(lineCode: Int): Flow<Result<ArrivalForecast>> {
        return flow {
            emit(Result.Loading())
            try {
                val response = spTransApi.getArrivalForecastForLine(lineCode)
                emit(Result.Success(response))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }
    }

    override suspend fun getArrivalForecastForStop(stopCode: Int): Flow<Result<DataResponse>> {
        return flow {
            emit(Result.Loading())
            try {
                val response = spTransApi.getArrivalForecastForStop(stopCode)
                emit(Result.Success(response))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }
    }
}