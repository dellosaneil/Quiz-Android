package com.thelazybattley.domain.network.usecase

import com.thelazybattley.common.enums.QuestionType
import com.thelazybattley.domain.model.Question

interface AddQuestionUseCase {

    suspend operator fun invoke(
        question: String,
        answer: String,
        choices: List<String>,
        type: QuestionType
    ): Result<Question>
}
