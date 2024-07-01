package com.example.trackbuss.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackbuss.domain.data.ArrivalForecast
import com.example.trackbuss.domain.data.BusLine
import com.example.trackbuss.domain.usecase.GetArrivalForecastForLineUseCase
import com.example.trackbuss.domain.usecase.GetArrivalForecastUseCase
import com.example.trackbuss.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetArrivalForecastForLineViwModel @Inject constructor(private val getArrivalForecastForLineUseCase: GetArrivalForecastForLineUseCase) :
    ViewModel() {

    private val _data = MutableStateFlow<ArrivalForecast?>(null)
    val data: StateFlow<ArrivalForecast?> = _data

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _isError = MutableStateFlow("")
    val isError = _isError.asStateFlow()

    fun getArrivalForecastForLine(lineCode: Int) {
        viewModelScope.launch {
            getArrivalForecastForLineUseCase(lineCode).collect { result ->
                when (result) {
                    is Result.Error -> {
                        val error = result.message
                        _isLoading.value = false
                        if (error != null) {
                            _isError.value = error
                        }
                    }

                    is Result.Loading -> {
                        _isLoading.value = true
                    }

                    is Result.Success -> {
                        _isLoading.value = false
                        val data = result.data
                        if (data != null) {
                            _data.value = data
                        }
                    }
                }
            }
        }
    }
}