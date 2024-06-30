package com.example.trackbuss.domain.usecase

import com.example.trackbuss.domain.repository.SpTransRepository
import javax.inject.Inject

class GetArrivalForecastForStopUseCase @Inject constructor(private val spTransRepository: SpTransRepository) {
    suspend operator fun invoke(topCode: Int) = spTransRepository.getArrivalForecastForStop(topCode)
}