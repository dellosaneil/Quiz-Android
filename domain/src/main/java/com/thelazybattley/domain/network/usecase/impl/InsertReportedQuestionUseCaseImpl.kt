package com.thelazybattley.domain.network.usecase.impl

import com.thelazybattley.common.enums.QuizType
import com.thelazybattley.domain.network.QuizRepository
import com.thelazybattley.domain.network.usecase.InsertReportedQuestionUseCase
import javax.inject.Inject

class InsertReportedQuestionUseCaseImpl @Inject constructor(
    private val repository: QuizRepository
) : InsertReportedQuestionUseCase {
    override suspend fun invoke(
        question: String,
        questionId: Int,
        suggestedAnswer: String,
        quizType: QuizType
    ) = repository.insertReportedQuestion(
        suggestedAnswer = suggestedAnswer,
        questionId = questionId,
        question = question,
        quizType = quizType
    )
}
