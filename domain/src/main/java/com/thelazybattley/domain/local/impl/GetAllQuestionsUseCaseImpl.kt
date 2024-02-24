package com.thelazybattley.domain.local.impl

import com.thelazybattley.common.enums.QuizType
import com.thelazybattley.domain.local.GetAllQuestionsUseCase
import com.thelazybattley.domain.network.QuizRepository
import javax.inject.Inject

class GetAllQuestionsUseCaseImpl @Inject constructor(
    private val repository: QuizRepository
): GetAllQuestionsUseCase {
    override suspend fun invoke(count : Int, quizType: QuizType) = repository.getAllQuestions(count = count, quizType = quizType)
}
