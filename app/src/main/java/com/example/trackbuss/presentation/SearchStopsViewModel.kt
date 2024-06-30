package com.example.trackbuss.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackbuss.domain.data.BusStop
import com.example.trackbuss.domain.usecase.SearchStopsUseCase
import com.example.trackbuss.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchStopsViewModel @Inject constructor(private val searchStopsUseCase: SearchStopsUseCase) :
    ViewModel() {
    private val _data =
        MutableStateFlow<Result<List<BusStop>>>(Result.Loading())
    val data: StateFlow<Result<List<BusStop>>> = _data

    fun searchStops(searchTerm: String) {
        viewModelScope.launch {
            searchStopsUseCase(searchTerm).collect {
                _data.value = it
            }
        }
    }
}