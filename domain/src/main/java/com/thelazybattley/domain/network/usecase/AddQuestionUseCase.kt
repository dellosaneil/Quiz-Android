package com.thelazybattley.domain.network.usecase

import com.thelazybattley.common.enums.QuizType

interface AddQuestionUseCase {

    suspend operator fun invoke(
        question: String,
        answer: String,
        choices: List<String>,
        category: String,
        quizType: QuizType
    ): Result<Unit>
}
