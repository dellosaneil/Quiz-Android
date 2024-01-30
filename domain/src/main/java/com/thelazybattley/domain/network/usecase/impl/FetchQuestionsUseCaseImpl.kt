package com.thelazybattley.domain.network.usecase.impl

import com.thelazybattley.domain.network.QuizRepository
import com.thelazybattley.domain.network.usecase.FetchQuestionsUseCase
import javax.inject.Inject

class FetchQuestionsUseCaseImpl @Inject constructor(
    private val repository: QuizRepository
): FetchQuestionsUseCase {
    override suspend fun invoke() = repository.fetchQuestions()
}
