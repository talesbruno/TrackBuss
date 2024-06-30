package com.example.trackbuss.domain.usecase

import com.example.trackbuss.domain.repository.SpTransRepository
import javax.inject.Inject

class SearchLinesUseCase @Inject constructor(private val spTransRepository: SpTransRepository) {
    suspend operator fun invoke(searchTerm: String) = spTransRepository.searchLines(searchTerm)
}