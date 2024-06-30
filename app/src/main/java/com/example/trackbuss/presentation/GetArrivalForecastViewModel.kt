package com.example.trackbuss.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackbuss.domain.data.ArrivalForecast
import com.example.trackbuss.domain.data.BusLine
import com.example.trackbuss.domain.usecase.GetArrivalForecastUseCase
import com.example.trackbuss.domain.usecase.SearchLinesUseCase
import com.example.trackbuss.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.http.Query
import javax.inject.Inject

@HiltViewModel
class GetArrivalForecastViewModel @Inject constructor(private val getArrivalForecastUseCase: GetArrivalForecastUseCase) :
    ViewModel() {
    private val _data =
        MutableStateFlow<Result<List<ArrivalForecast>>>(Result.Loading())
    val data: StateFlow<Result<List<ArrivalForecast>>> = _data

    fun searchStops(stopCode: Int, lineCode: String) {
        viewModelScope.launch {
            getArrivalForecastUseCase(stopCode, lineCode).collect {
                _data.value = it
            }
        }
    }
}