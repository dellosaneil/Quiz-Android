package com.thelazybattley.domain.local

import com.thelazybattley.domain.model.QuizResult
import kotlinx.coroutines.flow.Flow

interface GetAllQuizResultsUseCase {

    suspend operator fun invoke() : Result<Flow<List<QuizResult>>>
}
