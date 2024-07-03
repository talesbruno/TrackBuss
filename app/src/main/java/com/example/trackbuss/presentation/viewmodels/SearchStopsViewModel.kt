package com.example.trackbuss.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackbuss.domain.data.BusLine
import com.example.trackbuss.domain.data.BusStop
import com.example.trackbuss.domain.usecase.SearchStopsUseCase
import com.example.trackbuss.states.SearchEvent
import com.example.trackbuss.states.SearchState
import com.example.trackbuss.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchStopsViewModel @Inject constructor(
    private val searchStopsUseCase: SearchStopsUseCase
) :
    ViewModel() {
    var searchState by mutableStateOf(SearchState())

    private val _data = MutableStateFlow<List<BusStop>>(emptyList())
    val data: StateFlow<List<BusStop>> = _data

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _isError = MutableStateFlow("")
    val isError = _isError.asStateFlow()


    fun searchStops(searchTerm: Int) {
        viewModelScope.launch {
            searchStopsUseCase(searchTerm).collect { result ->
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

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.QueryChanged -> {
                searchState = searchState.copy(query = event.query, active = true)
            }
            SearchEvent.Submit -> {

            }
        }
    }
}