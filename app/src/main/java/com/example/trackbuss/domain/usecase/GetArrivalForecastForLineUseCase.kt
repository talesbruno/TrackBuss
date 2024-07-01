package com.example.trackbuss.domain.usecase

import com.example.trackbuss.domain.repository.SpTransRepository
import javax.inject.Inject

class GetArrivalForecastForLineUseCase @Inject constructor(private val spTransRepository: SpTransRepository) {
    suspend operator fun invoke(lineCode: Int) = spTransRepository.getArrivalForecastForLine(lineCode)
}