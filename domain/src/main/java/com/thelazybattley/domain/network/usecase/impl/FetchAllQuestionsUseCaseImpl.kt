package com.thelazybattley.domain.network.usecase.impl

import com.thelazybattley.common.enums.QuizType
import com.thelazybattley.domain.network.QuizRepository
import com.thelazybattley.domain.network.usecase.FetchAllQuestionsUseCase
import javax.inject.Inject

class FetchAllQuestionsUseCaseImpl @Inject constructor(
    private val repository: QuizRepository
): FetchAllQuestionsUseCase {
    override suspend fun invoke(quizType: QuizType) = repository.fetchAllQuestions(quizType = quizType)
}
