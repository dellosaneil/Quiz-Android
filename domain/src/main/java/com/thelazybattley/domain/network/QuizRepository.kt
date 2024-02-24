package com.thelazybattley.domain.network

import com.thelazybattley.common.enums.QuizType
import com.thelazybattley.common.model.Question
import com.thelazybattley.common.model.QuizDetailsState
import com.thelazybattley.domain.model.QuizResult

interface QuizRepository {

    suspend fun fetchAllQuestions(
        quizType: QuizType
    ): Result<List<Question>>

    suspend fun addQuestion(
        question: String,
        answer: String,
        choices: List<String>,
        category: String,
        quizType: QuizType
    ): Result<Unit>

    suspend fun insertReportedQuestion(
        suggestedAnswer: String,
        questionId: Int,
        question: String
    ): Result<Unit>

    suspend fun getAllQuestions(count: Int, quizType: QuizType): Result<List<Question>>

    suspend fun insertAllQuestions(questions: List<Question>): Result<Unit>

    suspend fun getQuestionsByCategory(
        category: String,
        count: Int
    ): Result<List<Question>>

    suspend fun getAllQuizResult() : Result<List<QuizResult>>

    suspend fun clearAllQuizResult() : Result<Unit>

    suspend fun insertQuizResult(
        quizDetailsState: QuizDetailsState
    ) : Result<Unit>
}
