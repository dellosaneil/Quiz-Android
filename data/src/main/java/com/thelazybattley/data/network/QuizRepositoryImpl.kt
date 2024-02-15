package com.thelazybattley.data.network

import com.thelazybattley.common.enums.QuestionCategory
import com.thelazybattley.common.enums.toQuestionCategory
import com.thelazybattley.data.network.payload.QuestionPayload
import com.thelazybattley.data.network.payload.ReportQuestionPayload
import com.thelazybattley.data.network.service.QuizService
import com.thelazybattley.domain.model.Question
import com.thelazybattley.domain.network.QuizRepository
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val service: QuizService
) : QuizRepository {
    override suspend fun fetchQuestions() = runCatching {
        service.fetchQuestions().map { response ->
            Question(
                id = response.id,
                answer = response.answer,
                question = response.question,
                choices = response.choices,
                category = response.category.toQuestionCategory
            )
        }
    }

    override suspend fun addQuestion(
        question: String,
        answer: String,
        choices: List<String>,
        type: QuestionCategory
    ) = runCatching {
        service.addQuestion(
            payload = QuestionPayload(
                question = question,
                answer = answer,
                choices = choices,
                category = type.name
            )
        ).run {
            Question(
                id = id,
                answer = this.answer,
                question = this.question,
                choices = this.choices,
                category = this.category.toQuestionCategory
            )
        }
    }

    override suspend fun insertReportedQuestion(
        suggestedAnswer: String,
        questionId: Int,
        question: String
    ) = runCatching {
        service.insertReportedQuestion(
            payload = ReportQuestionPayload(
                questionId = questionId,
                suggestedAnswer = suggestedAnswer,
                question = question
            )
        )
    }

    override suspend fun fetchCategoriesDetails() = runCatching {
        service.fetchCategoriesDetails().map { response ->
            response.toData
        }
    }
}
