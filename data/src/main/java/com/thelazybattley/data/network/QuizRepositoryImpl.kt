package com.thelazybattley.data.network

import com.thelazybattley.common.enums.QuestionCategory
import com.thelazybattley.data.local.dao.QuestionsDao
import com.thelazybattley.data.mapper.toData
import com.thelazybattley.data.mapper.toEntity
import com.thelazybattley.data.network.payload.QuestionPayload
import com.thelazybattley.data.network.payload.ReportQuestionPayload
import com.thelazybattley.data.network.service.QuizService
import com.thelazybattley.domain.model.Question
import com.thelazybattley.domain.network.QuizRepository
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val service: QuizService,
    private val dao: QuestionsDao
) : QuizRepository {
    override suspend fun fetchAllQuestions() = runCatching {
        service.fetchQuestions().map { response ->
            response.toData
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
        ).toData
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

    override suspend fun getAllQuestions() = runCatching {
        dao.getAll().map { entity ->
            entity
                .toData
                .copy(
                    choices = entity.choices.shuffled()
                )
        }
    }

    override suspend fun insertAllQuestions(questions: List<Question>) = runCatching {
        dao.insertAll(questions = questions.map { it.toEntity })
    }
}
