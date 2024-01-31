package com.thelazybattley.domain.network.usecase.impl

import com.thelazybattley.common.enums.QuestionType
import com.thelazybattley.domain.network.QuizRepository
import com.thelazybattley.domain.network.usecase.AddQuestionUseCase
import javax.inject.Inject

class AddQuestionUseCaseImpl @Inject constructor(
    private val repository: QuizRepository
) : AddQuestionUseCase {
    override suspend fun invoke(
        question: String,
        answer: String,
        choices: List<String>,
        type: QuestionType
    ) = repository.addQuestion(
            question = question, answer = answer, choices = choices,
            type = type
        )
}
