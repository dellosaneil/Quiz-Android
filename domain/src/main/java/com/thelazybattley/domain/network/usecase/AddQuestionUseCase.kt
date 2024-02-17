package com.thelazybattley.domain.network.usecase

import com.thelazybattley.common.enums.QuestionCategory
import com.thelazybattley.common.model.Question

interface AddQuestionUseCase {

    suspend operator fun invoke(
        question: String,
        answer: String,
        choices: List<String>,
        type: QuestionCategory
    ): Result<Question>
}
