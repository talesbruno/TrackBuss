package com.example.trackbuss.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackbuss.domain.data.DataResponse
import com.example.trackbuss.domain.usecase.GetBussPositionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.trackbuss.utils.Result
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BussPositionsViewModel @Inject constructor(private val getBussPositionsUseCase: GetBussPositionsUseCase) :
    ViewModel() {
    private val _data =
        MutableStateFlow<Result<DataResponse>>(Result.Loading())
    val data: StateFlow<Result<DataResponse>> = _data

    fun getBussPositions() {
        viewModelScope.launch {
            getBussPositionsUseCase().collect {
                _data.value = it
            }
        }
    }
}