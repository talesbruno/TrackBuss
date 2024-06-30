package com.example.trackbuss.domain.usecase

import com.example.trackbuss.domain.repository.SpTransRepository
import javax.inject.Inject

class GetArrivalForecastUseCase @Inject constructor(private val spTransRepository: SpTransRepository) {
    suspend operator fun invoke(stopCode: Int, lineCode: String) = spTransRepository.getArrivalForecast(stopCode, lineCode)
}