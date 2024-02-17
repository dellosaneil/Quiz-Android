package com.thelazybattley.domain.local.impl

import com.thelazybattley.domain.local.GetAllQuizResultsUseCase
import com.thelazybattley.domain.network.QuizRepository
import javax.inject.Inject

class GetAllQuizResultsUseCaseImpl @Inject constructor(
    private val repository: QuizRepository
): GetAllQuizResultsUseCase {

    override suspend fun invoke() = repository.getAllQuizResult()
}
