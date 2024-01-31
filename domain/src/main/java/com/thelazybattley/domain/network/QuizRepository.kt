package com.thelazybattley.domain.network

import com.thelazybattley.common.enums.QuestionType
import com.thelazybattley.domain.model.Question

interface QuizRepository {

    suspend fun fetchQuestions(): Result<List<Question>>

    suspend fun addQuestion(
        question: String,
        answer: String,
        choices: List<String>,
        type: QuestionType
    ): Result<Question>

}
