package com.thelazybattley.domain.network.usecase

import com.thelazybattley.common.enums.QuizType

interface InsertReportedQuestionUseCase {

    suspend operator fun invoke(
        question: String,
        questionId: Int,
        suggestedAnswer: String,
        quizType: QuizType
    ) : Result<Unit>
}
