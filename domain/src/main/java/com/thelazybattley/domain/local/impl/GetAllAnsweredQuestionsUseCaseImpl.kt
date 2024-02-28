package com.thelazybattley.domain.local.impl

import com.thelazybattley.domain.local.GetAllAnsweredQuestionsUseCase
import com.thelazybattley.domain.network.QuizRepository
import javax.inject.Inject

class GetAllAnsweredQuestionsUseCaseImpl @Inject constructor(
    private val repository: QuizRepository
): GetAllAnsweredQuestionsUseCase {
    override suspend fun invoke() = repository.getAllAnsweredQuestions()
}
